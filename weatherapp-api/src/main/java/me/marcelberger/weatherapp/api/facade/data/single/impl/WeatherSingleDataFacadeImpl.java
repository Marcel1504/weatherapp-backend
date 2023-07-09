package me.marcelberger.weatherapp.api.facade.data.single.impl;

import me.marcelberger.weatherapp.api.dto.response.data.single.WeatherSingleDataResponseDto;
import me.marcelberger.weatherapp.api.facade.data.single.SingleDataFacade;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherSingleDataFacadeImpl extends SingleDataFacade<WeatherDataEntity, WeatherSingleDataResponseDto> {
    @Override
    protected String getLatestDataNotFoundMessageKey() {
        return "weather.notFound";
    }
}
