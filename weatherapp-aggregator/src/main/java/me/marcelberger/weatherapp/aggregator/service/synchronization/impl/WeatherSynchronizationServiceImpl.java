package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherSynchronizationServiceImpl extends SynchronizationService<WeatherDataEntity> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.WEATHER;
    }

    @Override
    protected LocalDateTime getLatestSourceTimestamp(StationEntity station) {
        return weatherRepository.findLatestByStationId(station.getId()).getTimestamp();
    }
}
