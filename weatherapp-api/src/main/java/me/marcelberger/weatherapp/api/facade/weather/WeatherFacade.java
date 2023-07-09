package me.marcelberger.weatherapp.api.facade.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherData;

public interface WeatherFacade {
    WeatherData getLatest(String stationCode);
}
