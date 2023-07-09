package me.marcelberger.weatherapp.api.facade.weather.year;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;

public interface WeatherYearFacade {
    WeatherSummaryData getYearForStation(String stationCode, String year);

    PageData<WeatherSummaryData> getAllYearsForStation(String stationCode,
                                                       Integer page,
                                                       Integer size,
                                                       WeatherSortEnum sort);
}
