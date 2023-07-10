package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherSynchronizationServiceImpl extends SynchronizationService<WeatherSingleDataEntity> {

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.WEATHER;
    }

    @Override
    protected LocalDateTime getTimestampOfSourceEntity(WeatherSingleDataEntity entity) {
        return entity.getTimestamp();
    }

}
