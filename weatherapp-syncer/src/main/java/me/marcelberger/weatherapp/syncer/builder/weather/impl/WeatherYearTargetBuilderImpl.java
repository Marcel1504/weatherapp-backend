package me.marcelberger.weatherapp.syncer.builder.weather.impl;

import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherYearEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import me.marcelberger.weatherapp.syncer.builder.weather.WeatherTargetBuilder;

public class WeatherYearTargetBuilderImpl extends WeatherTargetBuilder<WeatherYearEntity> {
    public WeatherYearTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
