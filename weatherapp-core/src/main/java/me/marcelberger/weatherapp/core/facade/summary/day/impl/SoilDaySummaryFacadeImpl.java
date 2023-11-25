package me.marcelberger.weatherapp.core.facade.summary.day.impl;

import me.marcelberger.weatherapp.core.data.summary.day.SoilDaySummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.facade.summary.day.DaySummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilDaySummaryFacadeImpl extends DaySummaryFacade<SoilDaySummaryEntity, SoilDaySummaryData, SoilSortEnum> {

    @Override
    protected CoreError.Code getDaySummaryNotFoundErrorCode() {
        return CoreError.Code.CORE00500;
    }
}
