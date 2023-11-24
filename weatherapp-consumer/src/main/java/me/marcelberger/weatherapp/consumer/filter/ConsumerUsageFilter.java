package me.marcelberger.weatherapp.consumer.filter;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.stereotype.Component;

@Component
public class ConsumerUsageFilter extends UsageFilter {
    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.CONSUMER;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return 3L;
    }
}
