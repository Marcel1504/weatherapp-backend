package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherDayEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherDayTargetBuilderImpl extends WeatherTargetBuilder<WeatherDayEntity> {
    public WeatherDayTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
