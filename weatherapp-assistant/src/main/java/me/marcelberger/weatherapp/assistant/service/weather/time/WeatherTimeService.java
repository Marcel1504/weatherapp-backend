package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.assistant.data.weather.time.WeatherTimeData;
import me.marcelberger.weatherapp.core.data.station.StationData;

public interface WeatherTimeService {
    WeatherTimeData getWeatherDayOrNull(String date, StationData station);

    WeatherTimeData getWeatherMonthOrNull(String year, String month, StationData station);

    WeatherTimeData getWeatherYearOrNull(String year, StationData station);
}
