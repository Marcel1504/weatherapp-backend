package me.marcelberger.weatherapp.aggregator.service.aggregation.hour.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherHourTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.hour.HourAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherHourAggregationServiceImpl extends HourAggregationService<WeatherSingleSummaryEntity, WeatherHourSummaryEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleSummaryEntity, WeatherHourSummaryEntity> createTargetBuilder() {
        return new WeatherHourTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherHourSummaryEntity createBaseTarget(StationEntity station, String day, String hour) {
        return WeatherHourSummaryEntity.builder()
                .station(station)
                .day(day)
                .hour(hour)
                .build();
    }
}
