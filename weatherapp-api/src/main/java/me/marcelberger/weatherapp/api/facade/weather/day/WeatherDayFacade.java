package me.marcelberger.weatherapp.api.facade.weather.day;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;

public interface WeatherDayFacade {
    PageData<WeatherSummaryData> getDaysOfMonth(String stationCode, String month, String year);

    PageData<WeatherSummaryData> getAllDays(String stationCode,
                                            Integer page,
                                            Integer size,
                                            String startDay,
                                            String endDay,
                                            WeatherSortEnum sort);

    WeatherSummaryData getDay(String stationCode, String day);
}
