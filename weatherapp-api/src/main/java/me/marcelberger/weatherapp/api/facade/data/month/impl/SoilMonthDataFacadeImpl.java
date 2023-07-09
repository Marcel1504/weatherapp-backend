package me.marcelberger.weatherapp.api.facade.data.month.impl;

import me.marcelberger.weatherapp.api.dto.response.data.month.SoilMonthDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.facade.data.month.MonthDataFacade;
import me.marcelberger.weatherapp.core.entity.data.month.SoilMonthDataEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilMonthDataFacadeImpl extends MonthDataFacade<SoilMonthDataEntity, SoilMonthDataResponseDto, SoilSortEnum> {
    @Override
    protected String getMonthDataNotFoundMessageKey() {
        return "soil.notFound";
    }
}
