package me.marcelberger.weatherapp.core.facade.summary.single.impl;

import me.marcelberger.weatherapp.core.data.summary.single.SoilSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.facade.summary.single.SingleSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class SoilSingleDataFacadeImpl extends SingleSummaryFacade<SoilSingleSummaryEntity, SoilSingleSummaryData> {

    @Override
    protected ErrorCodeEnum getSingleSummaryNotFoundErrorCode() {
        return ErrorCodeEnum.CODE00201;
    }
}
