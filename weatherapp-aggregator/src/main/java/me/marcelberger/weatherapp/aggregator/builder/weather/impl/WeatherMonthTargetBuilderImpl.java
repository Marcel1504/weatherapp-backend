package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherMonthTargetBuilderImpl extends WeatherTargetBuilder<WeatherMonthSummaryEntity> {
    public WeatherMonthTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
