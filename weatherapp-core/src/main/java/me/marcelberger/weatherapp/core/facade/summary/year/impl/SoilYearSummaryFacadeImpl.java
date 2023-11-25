package me.marcelberger.weatherapp.core.facade.summary.year.impl;

import me.marcelberger.weatherapp.core.data.summary.year.SoilYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.SoilYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilYearSummaryFacadeImpl extends YearSummaryFacade<
        SoilYearSummaryEntity,
        SoilYearSummaryData,
        SoilSortEnum> {

    @Override
    protected CoreError.Code getYearSummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00500;
    }
}
