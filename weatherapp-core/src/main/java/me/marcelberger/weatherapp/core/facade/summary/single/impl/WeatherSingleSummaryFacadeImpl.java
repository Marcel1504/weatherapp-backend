package me.marcelberger.weatherapp.core.facade.summary.single.impl;

import me.marcelberger.weatherapp.core.data.summary.single.WeatherSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.facade.summary.single.SingleSummaryFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherSingleSummaryFacadeImpl extends SingleSummaryFacade<
        WeatherSingleSummaryEntity,
        WeatherSingleSummaryData> {

    @Override
    protected ErrorCodeEnum getSingleSummaryNotFoundErrorCode() {
        return ErrorCodeEnum.CODE00101;
    }
}
