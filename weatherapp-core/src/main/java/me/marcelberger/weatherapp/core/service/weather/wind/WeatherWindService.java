package me.marcelberger.weatherapp.core.service.weather.wind;

import me.marcelberger.weatherapp.core.enumeration.weather.WeatherWindDirectionEnum;

public interface WeatherWindService {
    WeatherWindDirectionEnum degreeToWindDirectionEnum(Integer degree);
}
