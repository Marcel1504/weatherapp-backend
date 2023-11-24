package me.marcelberger.weatherapp.api.filter;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.stereotype.Component;

@Component
public class ApiUsageFilter extends UsageFilter {
    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.API;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return 30L;
    }
}
