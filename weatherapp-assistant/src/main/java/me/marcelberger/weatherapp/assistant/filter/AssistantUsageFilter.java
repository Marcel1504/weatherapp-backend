package me.marcelberger.weatherapp.assistant.filter;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.stereotype.Component;

@Component
public class AssistantUsageFilter extends UsageFilter {

    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.ASSISTANT;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return 3L;
    }
}
