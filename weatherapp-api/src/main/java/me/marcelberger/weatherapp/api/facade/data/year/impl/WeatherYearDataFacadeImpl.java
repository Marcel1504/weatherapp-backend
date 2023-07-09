package me.marcelberger.weatherapp.api.facade.data.year.impl;

import me.marcelberger.weatherapp.api.dto.response.data.year.WeatherYearDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.year.YearDataFacade;
import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherYearDataFacadeImpl extends YearDataFacade<WeatherYearDataEntity, WeatherYearDataResponseDto, WeatherSortEnum> {
    @Override
    protected String getYearDataNotFoundMessageKey() {
        return "weather.notFound";
    }
}
