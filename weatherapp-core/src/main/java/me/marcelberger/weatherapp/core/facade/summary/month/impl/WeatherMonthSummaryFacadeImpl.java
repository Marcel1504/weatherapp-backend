package me.marcelberger.weatherapp.core.facade.summary.month.impl;

import me.marcelberger.weatherapp.core.data.summary.month.WeatherMonthSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.month.MonthSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherMonthSummaryFacadeImpl extends MonthSummaryFacade<
        WeatherMonthSummaryEntity,
        WeatherMonthSummaryData,
        WeatherSortEnum> {
    @Override
    protected CoreError.Code getMonthSummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00400;
    }
}
