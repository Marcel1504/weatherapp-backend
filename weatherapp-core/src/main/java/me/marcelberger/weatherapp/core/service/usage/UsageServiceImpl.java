package me.marcelberger.weatherapp.core.service.usage;

import me.marcelberger.weatherapp.core.entity.usage.UsageEntity;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.repository.usage.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsageServiceImpl implements UsageService {

    @Autowired
    private UsageRepository usageRepository;

    @Override
    public void updateUsageForIPAddressAndModuleName(String ipAddress, UsageModuleNameEnum moduleName) {
        if (ipAddress == null || moduleName == null) {
            throw new CoreError(
                    CoreError.Code.CORE00002,
                    "Usage can not be updated: IP address or module name not provided");
        }
        UsageEntity usage = usageRepository.findByIpAddressAndModuleName(ipAddress, moduleName).orElse(null);
        if (usage == null) {
            usage = UsageEntity.builder()
                    .ipAddress(ipAddress)
                    .moduleName(moduleName)
                    .totalRequests(0L)
                    .build();
        }
        usage.setTotalRequests(usage.getTotalRequests() + 1L);
        usage.setLastActivity(LocalDateTime.now());
        usageRepository.save(usage);
    }
}
