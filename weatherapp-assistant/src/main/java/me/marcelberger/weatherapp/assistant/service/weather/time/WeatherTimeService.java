package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.assistant.data.weather.time.WeatherTimeData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

public interface WeatherTimeService {
    WeatherTimeData getWeatherDayOrNull(String date, StationEntity station);

    WeatherTimeData getWeatherMonthOrNull(String year, String month, StationEntity station);

    WeatherTimeData getWeatherYearOrNull(String year, StationEntity station);
}
