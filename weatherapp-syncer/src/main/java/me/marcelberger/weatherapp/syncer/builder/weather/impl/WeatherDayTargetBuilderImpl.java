package me.marcelberger.weatherapp.syncer.builder.weather.impl;

import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherDayEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import me.marcelberger.weatherapp.syncer.builder.weather.WeatherTargetBuilder;

public class WeatherDayTargetBuilderImpl extends WeatherTargetBuilder<WeatherDayEntity> {
    public WeatherDayTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
