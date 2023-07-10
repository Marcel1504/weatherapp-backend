package me.marcelberger.weatherapp.aggregator.service.aggregation.hour.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherHourTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.hour.HourAggregationService;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherHourAggregationServiceImpl extends HourAggregationService<WeatherSingleDataEntity, WeatherHourDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleDataEntity, WeatherHourDataEntity> createTargetBuilder() {
        return new WeatherHourTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherHourDataEntity createBaseTarget(StationEntity station, String day, String hour) {
        return WeatherHourDataEntity.builder()
                .station(station)
                .day(day)
                .hour(hour)
                .build();
    }
}
