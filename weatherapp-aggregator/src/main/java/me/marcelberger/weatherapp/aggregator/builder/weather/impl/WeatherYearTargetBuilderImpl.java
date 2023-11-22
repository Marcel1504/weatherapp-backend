package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherYearTargetBuilderImpl extends WeatherTargetBuilder<WeatherYearSummaryEntity> {
    public WeatherYearTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
