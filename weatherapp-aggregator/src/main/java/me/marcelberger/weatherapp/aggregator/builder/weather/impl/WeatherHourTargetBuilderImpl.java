package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherHourTargetBuilderImpl extends WeatherTargetBuilder<WeatherHourSummaryEntity> {
    public WeatherHourTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
