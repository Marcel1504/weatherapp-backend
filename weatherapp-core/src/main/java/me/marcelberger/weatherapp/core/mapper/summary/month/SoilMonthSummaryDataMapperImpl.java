package me.marcelberger.weatherapp.core.mapper.summary.month;

import me.marcelberger.weatherapp.core.data.summary.month.SoilMonthSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.month.SoilMonthSummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SoilMonthSummaryDataMapperImpl implements Mapper<SoilMonthSummaryEntity, SoilMonthSummaryData> {
    @Override
    public SoilMonthSummaryData map(SoilMonthSummaryEntity object) {
        return SoilMonthSummaryData.builder()
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
                .month(object.getMonth())
                .year(object.getYear())
                .stationId(object.getStation().getId())
                .build();
    }
}
