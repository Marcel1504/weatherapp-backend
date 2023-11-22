package me.marcelberger.weatherapp.assistant.service.weather.time;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;

public interface WeatherTimeService {
    WeatherDaySummaryEntity getWeatherDay(String date, StationEntity station);

    WeatherMonthSummaryEntity getWeatherMonth(String year, String month, StationEntity station);

    WeatherYearSummaryEntity getWeatherYear(String year, StationEntity station);
}
