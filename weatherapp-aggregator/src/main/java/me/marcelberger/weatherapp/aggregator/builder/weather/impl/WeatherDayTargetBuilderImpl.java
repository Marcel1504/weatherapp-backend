package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherDayTargetBuilderImpl extends WeatherTargetBuilder<WeatherDayDataEntity> {
    public WeatherDayTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
