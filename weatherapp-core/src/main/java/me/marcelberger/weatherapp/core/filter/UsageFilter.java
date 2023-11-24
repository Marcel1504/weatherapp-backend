package me.marcelberger.weatherapp.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.service.usage.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableScheduling
public abstract class UsageFilter extends OncePerRequestFilter {

    private final Map<String, Long> requestCount = new HashMap<>();
    @Autowired
    private UsageService usageService;

    /**
     * Updates Usage and enforces rate limits for the IP address of a request
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = getRealIPAddress(request);
        UsageModuleNameEnum moduleName = getModuleName();
        if (ipAddress != null && moduleName != null) {
            usageService.saveOrCreateUsageForIPAddressAndModuleName(ipAddress, moduleName);
        }
        if (ipAddress == null || isRateLimitExceeded(ipAddress)) {
            response.setStatus(429);
            response.getWriter().write(String.format("Too many requests for IP %s", ipAddress));
        } else {
            incrementRateLimit(ipAddress);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Tasks that runs every minute to reset the rate limits per IP address
     */
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void resetRateLimits() {
        log.debug("Resetting rate limits for Usage");
        requestCount.forEach((k, v) -> requestCount.put(k, 0L));
    }

    /**
     * Gets the original IP address of a request
     *
     * @param request HttpServletRequest
     * @return IP address
     */
    private String getRealIPAddress(HttpServletRequest request) {
        if (request != null) {
            String ip = request.getHeader("X-Real-IP");
            if (ip == null || ip.isBlank()) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
        throw new CoreException(ErrorCodeEnum.CODE00002);
    }

    /**
     * Checks, if the rate limit for an IP address was exceeded
     *
     * @param ipAddress IP address
     * @return true, if the rate limit was exceeded
     */
    private boolean isRateLimitExceeded(String ipAddress) {
        return requestCount.containsKey(ipAddress) && requestCount.get(ipAddress) >= getRequestsPerMinute();
    }

    /**
     * Increments the requests counter for an IP address
     *
     * @param ipAddress IP address
     */
    private void incrementRateLimit(String ipAddress) {
        Long currentRate = requestCount.get(ipAddress);
        requestCount.put(ipAddress, currentRate != null ? currentRate + 1 : 1);
    }

    protected abstract UsageModuleNameEnum getModuleName();

    protected abstract Long getRequestsPerMinute();
}
