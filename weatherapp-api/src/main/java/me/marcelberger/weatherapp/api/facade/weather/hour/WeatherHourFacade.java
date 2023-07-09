package me.marcelberger.weatherapp.api.facade.weather.hour;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;

public interface WeatherHourFacade {
    PageData<WeatherSummaryData> getHoursOfDayForStation(String stationCode, String day, WeatherSortEnum sort);
}
