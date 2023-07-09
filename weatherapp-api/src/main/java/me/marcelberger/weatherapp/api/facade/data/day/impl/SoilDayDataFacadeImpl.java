package me.marcelberger.weatherapp.api.facade.data.day.impl;

import me.marcelberger.weatherapp.api.dto.response.data.day.SoilDayDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.facade.data.day.DayDataFacade;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilDayDataFacadeImpl extends DayDataFacade<SoilDayDataEntity, SoilDayDataResponseDto, SoilSortEnum> {
    @Override
    protected String getDayDataNotFoundMessageKey() {
        return "soil.notFound";
    }
}
