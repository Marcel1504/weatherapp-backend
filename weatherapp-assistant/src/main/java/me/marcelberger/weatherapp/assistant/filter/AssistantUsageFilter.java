package me.marcelberger.weatherapp.assistant.filter;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AssistantUsageFilter extends UsageFilter {

    @Value("${weatherapp.usage.requestsPerMinute}")
    private Long requestsPerMinute;

    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.ASSISTANT;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return requestsPerMinute;
    }
}
