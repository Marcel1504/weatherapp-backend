package me.marcelberger.weatherapp.aggregator.builder.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.weather.WeatherTargetBuilder;
import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;

public class WeatherMonthTargetBuilderImpl extends WeatherTargetBuilder<WeatherMonthDataEntity> {
    public WeatherMonthTargetBuilderImpl(WeatherWindService weatherWindService) {
        super(weatherWindService);
    }
}
