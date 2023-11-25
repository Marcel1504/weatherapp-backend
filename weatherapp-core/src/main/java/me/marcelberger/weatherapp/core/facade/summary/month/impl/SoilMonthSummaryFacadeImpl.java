package me.marcelberger.weatherapp.core.facade.summary.month.impl;

import me.marcelberger.weatherapp.core.data.summary.month.SoilMonthSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.month.SoilMonthSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.month.MonthSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilMonthSummaryFacadeImpl extends MonthSummaryFacade<
        SoilMonthSummaryEntity,
        SoilMonthSummaryData,
        SoilSortEnum> {

    @Override
    protected CoreError.Code getMonthSummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00500;
    }
}
