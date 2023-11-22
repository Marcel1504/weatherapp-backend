package me.marcelberger.weatherapp.aggregator.service.aggregation.day.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.day.DayAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayAggregationServiceImpl extends DayAggregationService<WeatherSingleSummaryEntity, WeatherDaySummaryEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleSummaryEntity, WeatherDaySummaryEntity> createTargetBuilder() {
        return new WeatherDayTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherDaySummaryEntity createBaseTarget(StationEntity station, String day) {
        return WeatherDaySummaryEntity.builder()
                .station(station)
                .day(day)
                .build();
    }
}
