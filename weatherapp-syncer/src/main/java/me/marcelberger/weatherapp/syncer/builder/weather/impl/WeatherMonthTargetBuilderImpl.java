package me.marcelberger.weatherapp.syncer.builder.weather.impl;

import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherMonthEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import me.marcelberger.weatherapp.syncer.builder.weather.WeatherTargetBuilder;

public class WeatherMonthTargetBuilderImpl extends WeatherTargetBuilder<WeatherMonthEntity> {
    public WeatherMonthTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
