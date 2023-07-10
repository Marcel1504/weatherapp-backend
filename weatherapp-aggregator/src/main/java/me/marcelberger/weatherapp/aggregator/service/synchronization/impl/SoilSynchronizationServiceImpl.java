package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SoilSynchronizationServiceImpl extends SynchronizationService<SoilDataEntity> {

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.SOIL;
    }

    @Override
    protected LocalDateTime getTimestampOfSourceEntity(SoilDataEntity entity) {
        return entity.getTimestamp();
    }
}
