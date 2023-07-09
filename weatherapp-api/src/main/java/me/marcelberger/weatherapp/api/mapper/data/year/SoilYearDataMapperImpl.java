package me.marcelberger.weatherapp.api.mapper.data.year;

import me.marcelberger.weatherapp.api.dto.response.data.year.SoilYearDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
import org.springframework.stereotype.Component;

@Component
public class SoilYearDataMapperImpl implements DataMapper<SoilYearDataEntity, SoilYearDataResponseDto> {
    @Override
    public SoilYearDataResponseDto map(SoilYearDataEntity object) {
        return SoilYearDataResponseDto.builder()
                .temperature50cmAvg(object.getTemperature50cmAvg())
                .temperature50cmMax(object.getTemperature50cmMax())
                .temperature50cmMin(object.getTemperature50cmMin())
                .temperature100cmAvg(object.getTemperature100cmAvg())
                .temperature100cmMax(object.getTemperature100cmMax())
                .temperature100cmMin(object.getTemperature100cmMin())
                .temperature200cmAvg(object.getTemperature200cmAvg())
                .temperature200cmMax(object.getTemperature200cmMax())
                .temperature200cmMin(object.getTemperature200cmMin())
                .year(object.getYear())
                .build();
    }
}
