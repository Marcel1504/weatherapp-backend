package me.marcelberger.weatherapp.api.mapper.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.SoilSingleDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import org.springframework.stereotype.Component;

@Component
public class SoilDataMapperImpl implements Mapper<SoilDataEntity, SoilSingleDataResponseDto> {

    @Override
    public SoilSingleDataResponseDto map(SoilDataEntity object) {
        return SoilSingleDataResponseDto.builder()
                .temperature50cm(object.getTemperature50cm())
                .temperature100cm(object.getTemperature100cm())
                .temperature200cm(object.getTemperature200cm())
                .timestamp(object.getTimestamp())
                .build();
    }
}
