package me.marcelberger.weatherapp.core.repository.usage;

import me.marcelberger.weatherapp.core.entity.usage.UsageEntity;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsageRepository extends JpaRepository<UsageEntity, Long> {

    Optional<UsageEntity> findByIpAddressAndModuleName(String ipAddress, UsageModuleNameEnum moduleName);
}
