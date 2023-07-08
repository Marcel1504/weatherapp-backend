package me.marcelberger.weatherapp.syncer.builder.weather.impl;

import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherHourEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import me.marcelberger.weatherapp.syncer.builder.weather.WeatherTargetBuilder;

public class WeatherHourTargetBuilderImpl extends WeatherTargetBuilder<WeatherHourEntity> {
    public WeatherHourTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
