package me.marcelberger.weatherapp.api.mapper.data.day;

import me.marcelberger.weatherapp.api.dto.response.data.day.SoilDayDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import org.springframework.stereotype.Component;

@Component
public class SoilDayDataMapperImpl implements Mapper<SoilDayDataEntity, SoilDayDataResponseDto> {
    @Override
    public SoilDayDataResponseDto map(SoilDayDataEntity object) {
        return SoilDayDataResponseDto.builder()
                .temperature50cmAvg(object.getTemperature50cmAvg())
                .temperature50cmMax(object.getTemperature50cmMax())
                .temperature50cmMin(object.getTemperature50cmMin())
                .temperature100cmAvg(object.getTemperature100cmAvg())
                .temperature100cmMax(object.getTemperature100cmMax())
                .temperature100cmMin(object.getTemperature100cmMin())
                .temperature200cmAvg(object.getTemperature200cmAvg())
                .temperature200cmMax(object.getTemperature200cmMax())
                .temperature200cmMin(object.getTemperature200cmMin())
                .day(object.getDay())
                .build();
    }
}
