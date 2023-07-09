package me.marcelberger.weatherapp.api.facade.data.month.impl;

import me.marcelberger.weatherapp.api.dto.response.data.month.WeatherMonthDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.month.MonthDataFacade;
import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherMonthDataFacadeImpl extends MonthDataFacade<WeatherMonthDataEntity, WeatherMonthDataResponseDto, WeatherSortEnum> {
    @Override
    protected String getMonthDataNotFoundMessageKey() {
        return "weather.notFound";
    }
}
