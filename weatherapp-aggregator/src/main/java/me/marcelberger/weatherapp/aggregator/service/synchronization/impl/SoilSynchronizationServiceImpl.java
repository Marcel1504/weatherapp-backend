package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SoilSynchronizationServiceImpl extends SynchronizationService<SoilSingleDataEntity> {

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.SOIL;
    }

    @Override
    protected LocalDateTime getTimestampOfSourceEntity(SoilSingleDataEntity entity) {
        return entity.getTimestamp();
    }
}
