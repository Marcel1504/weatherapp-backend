package me.marcelberger.weatherapp.core.facade.summary.hour.impl;

import me.marcelberger.weatherapp.core.data.summary.hour.WeatherHourSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.hour.HourSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherHourSummaryFacadeImpl extends HourSummaryFacade<
        WeatherHourSummaryEntity,
        WeatherHourSummaryData,
        WeatherSortEnum> {
}
