package me.marcelberger.weatherapp.aggregator.service.aggregation.day.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.day.DayAggregationService;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilDayAggregationServiceImpl extends DayAggregationService<SoilSingleDataEntity, SoilDayDataEntity> {

    @Override
    protected TargetBuilder<SoilSingleDataEntity, SoilDayDataEntity> createTargetBuilder() {
        return new SoilDayTargetBuilderImpl();
    }

    @Override
    protected SoilDayDataEntity createBaseTarget(StationEntity station, String day) {
        return SoilDayDataEntity.builder()
                .station(station)
                .day(day)
                .build();
    }
}
