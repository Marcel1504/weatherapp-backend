package me.marcelberger.weatherapp.api.facade.data.day.impl;

import me.marcelberger.weatherapp.api.dto.response.data.day.WeatherDayDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.day.DayDataFacade;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherDayDataFacadeImpl extends DayDataFacade<WeatherDayDataEntity, WeatherDayDataResponseDto, WeatherSortEnum> {
    @Override
    protected String getDayDataNotFoundMessageKey() {
        return "weather.notFound";
    }
}
