package me.marcelberger.weatherapp.core.service.usage;

import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;

public interface UsageService {
    void updateUsageForIPAddressAndModuleName(String ipAddress, UsageModuleNameEnum moduleName);
}
