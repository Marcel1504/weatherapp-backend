package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherHourTargetBuilderImpl extends WeatherTargetBuilder<WeatherHourDataEntity> {
    public WeatherHourTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
