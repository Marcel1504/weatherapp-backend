package me.marcelberger.weatherapp.api.facade.data.single.impl;

import me.marcelberger.weatherapp.api.dto.response.data.single.SoilSingleDataResponseDto;
import me.marcelberger.weatherapp.api.facade.data.single.SingleDataFacade;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilSingleDataFacadeImpl extends SingleDataFacade<SoilDataEntity, SoilSingleDataResponseDto> {
    @Override
    protected String getLatestDataNotFoundMessageKey() {
        return "soil.notFound";
    }
}
