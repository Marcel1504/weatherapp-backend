package me.marcelberger.weatherapp.core.facade.summary.day.impl;

import me.marcelberger.weatherapp.core.data.summary.day.WeatherDaySummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.day.DaySummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherDaySummaryFacadeImpl extends DaySummaryFacade<
        WeatherDaySummaryEntity,
        WeatherDaySummaryData,
        WeatherSortEnum> {
    @Override
    protected CoreError.Code getDaySummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00400;
    }
}
