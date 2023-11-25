package me.marcelberger.weatherapp.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.data.error.ErrorData;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
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

    @Autowired
    private ObjectMapper objectMapper;

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
        String ipAddress = getRealIPAddressOrNull(request);
        if (ipAddress == null) {
            writeCoreErrorResponse(
                    ErrorData.<CoreError.Code>builder()
                            .code(CoreError.Code.CORE00002)
                            .message("Can not determine IP address")
                            .build(),
                    response,
                    400);
        } else {
            UsageModuleNameEnum moduleName = getModuleName();
            usageService.updateUsageForIPAddressAndModuleName(ipAddress, moduleName);
            if (isRateLimitExceeded(ipAddress)) {
                writeCoreErrorResponse(
                        ErrorData.<CoreError.Code>builder()
                                .code(CoreError.Code.CORE00003)
                                .message(String.format("Too many requests for IP address %s", ipAddress))
                                .build(),
                        response,
                        429);
            } else {
                incrementRateLimit(ipAddress);
                filterChain.doFilter(request, response);
            }
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
     * Gets the original IP address of a request, or null
     *
     * @param request HttpServletRequest
     * @return IP address or null
     */
    private String getRealIPAddressOrNull(HttpServletRequest request) {
        if (request != null) {
            String ip = request.getHeader("X-Real-IP");
            if (ip == null || ip.isBlank()) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
        return null;
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

    /**
     * Writes a CoreError to the response
     *
     * @param error    CoreError
     * @param response HttpServletResponse
     * @param status   HTTP-Status code
     */
    private void writeCoreErrorResponse(
            ErrorData<CoreError.Code> error,
            HttpServletResponse response,
            Integer status) throws IOException {
        response.setStatus(status != null ? status : 400);
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

    protected abstract UsageModuleNameEnum getModuleName();

    protected abstract Long getRequestsPerMinute();
}
