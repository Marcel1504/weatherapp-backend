package me.marcelberger.weatherapp.api.facade.data.hour.impl;

import me.marcelberger.weatherapp.api.dto.response.data.hour.WeatherHourDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.hour.HourDataFacade;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherHourDataFacadeImpl extends HourDataFacade<WeatherHourDataEntity, WeatherHourDataResponseDto, WeatherSortEnum> {
}
