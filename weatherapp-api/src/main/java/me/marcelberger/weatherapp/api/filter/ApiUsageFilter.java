package me.marcelberger.weatherapp.api.filter;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiUsageFilter extends UsageFilter {

    @Value("${weatherapp.usage.requestsPerMinute}")
    private Long requestsPerMinute;

    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.API;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return requestsPerMinute;
    }
}
