package me.marcelberger.weatherapp.api.facade.weather.month;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;

public interface WeatherMonthFacade {
    WeatherSummaryData getMonthForStation(String stationCode, String month, String year);

    PageData<WeatherSummaryData> getMonthsOfYearForStation(String stationCode,
                                                           Integer page,
                                                           Integer size,
                                                           String year,
                                                           WeatherSortEnum sort);
}
