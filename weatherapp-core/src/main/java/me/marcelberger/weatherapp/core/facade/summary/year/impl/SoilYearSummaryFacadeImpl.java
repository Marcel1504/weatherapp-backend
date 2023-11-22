package me.marcelberger.weatherapp.core.facade.summary.year.impl;

import me.marcelberger.weatherapp.core.data.summary.year.SoilYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.SoilYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilYearSummaryFacadeImpl extends YearSummaryFacade<
        SoilYearSummaryEntity,
        SoilYearSummaryData,
        SoilSortEnum> {

    @Override
    protected ErrorCodeEnum getYearSummaryNotFoundErrorCode() {
        return ErrorCodeEnum.CODE00200;
    }
}
