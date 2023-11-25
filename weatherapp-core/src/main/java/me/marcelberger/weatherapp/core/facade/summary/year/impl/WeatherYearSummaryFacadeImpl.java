package me.marcelberger.weatherapp.core.facade.summary.year.impl;

import me.marcelberger.weatherapp.core.data.summary.year.WeatherYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherYearSummaryFacadeImpl extends YearSummaryFacade<
        WeatherYearSummaryEntity,
        WeatherYearSummaryData,
        WeatherSortEnum> {

    @Override
    protected CoreError.Code getYearSummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00400;
    }
}
