package me.marcelberger.weatherapp.api.mapper.data.month;

import me.marcelberger.weatherapp.api.dto.response.data.month.SoilMonthDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.month.SoilMonthDataEntity;
import org.springframework.stereotype.Component;

@Component
public class SoilMonthDataMapperImpl implements Mapper<SoilMonthDataEntity, SoilMonthDataResponseDto> {
    @Override
    public SoilMonthDataResponseDto map(SoilMonthDataEntity object) {
        return SoilMonthDataResponseDto.builder()
                .temperature50cmAvg(object.getTemperature50cmAvg())
                .temperature50cmMax(object.getTemperature50cmMax())
                .temperature50cmMin(object.getTemperature50cmMin())
                .temperature100cmAvg(object.getTemperature100cmAvg())
                .temperature100cmMax(object.getTemperature100cmMax())
                .temperature100cmMin(object.getTemperature100cmMin())
                .temperature200cmAvg(object.getTemperature200cmAvg())
                .temperature200cmMax(object.getTemperature200cmMax())
                .temperature200cmMin(object.getTemperature200cmMin())
                .month(object.getMonth())
                .year(object.getYear())
                .build();
    }
}
