package me.marcelberger.weatherapp.core.mapper.summary.day;

import me.marcelberger.weatherapp.core.data.summary.day.SoilDaySummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SoilDaySummaryDataMapperImpl implements Mapper<SoilDaySummaryEntity, SoilDaySummaryData> {
    @Override
    public SoilDaySummaryData map(SoilDaySummaryEntity object) {
        return SoilDaySummaryData.builder()
                .amount(object.getAmount())
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
                .stationId(object.getStation().getId())
                .build();
    }
}
