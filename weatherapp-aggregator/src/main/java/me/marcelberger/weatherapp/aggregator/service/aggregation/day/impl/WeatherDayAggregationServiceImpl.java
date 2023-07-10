package me.marcelberger.weatherapp.aggregator.service.aggregation.day.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.day.DayAggregationService;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayAggregationServiceImpl extends DayAggregationService<WeatherSingleDataEntity, WeatherDayDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleDataEntity, WeatherDayDataEntity> createTargetBuilder() {
        return new WeatherDayTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherDayDataEntity createBaseTarget(StationEntity station, String day) {
        return WeatherDayDataEntity.builder()
                .station(station)
                .day(day)
                .build();
    }
}
