package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SoilSynchronizationServiceImpl extends SynchronizationService<SoilSingleSummaryEntity> {

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.SOIL;
    }

    @Override
    protected LocalDateTime getTimestampOfSourceEntity(SoilSingleSummaryEntity entity) {
        return entity.getTimestamp();
    }
}
