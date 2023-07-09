package me.marcelberger.weatherapp.api.facade.data.year.impl;

import me.marcelberger.weatherapp.api.dto.response.data.year.SoilYearDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.facade.data.year.YearDataFacade;
import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilYearDataFacadeImpl extends YearDataFacade<SoilYearDataEntity, SoilYearDataResponseDto, SoilSortEnum> {
    @Override
    protected String getYearDataNotFoundMessageKey() {
        return "soil.notFound";
    }
}
