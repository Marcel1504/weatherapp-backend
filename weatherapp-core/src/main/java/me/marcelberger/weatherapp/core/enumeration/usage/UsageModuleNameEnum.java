package me.marcelberger.weatherapp.core.enumeration.usage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsageModuleNameEnum {
    API("API"),
    ASSISTANT("ASSISTANT"),
    CONSUMER("CONSUMER");
    private final String value;
}
