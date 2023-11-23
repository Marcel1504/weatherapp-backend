package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.assistant.data.weather.WeatherTimeData;
import me.marcelberger.weatherapp.core.data.station.StationData;

public interface WeatherTimeService {
    WeatherTimeData getWeatherDay(String date, StationData station);

    WeatherTimeData getWeatherMonth(String year, String month, StationData station);

    WeatherTimeData getWeatherYear(String year, StationData station);
}
