package me.marcelberger.weatherapp.aggregator.service.aggregation.day.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.day.DayAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilDayAggregationServiceImpl extends DayAggregationService<SoilSingleSummaryEntity, SoilDaySummaryEntity> {

    @Override
    protected TargetBuilder<SoilSingleSummaryEntity, SoilDaySummaryEntity> createTargetBuilder() {
        return new SoilDayTargetBuilderImpl();
    }

    @Override
    protected SoilDaySummaryEntity createBaseTarget(StationEntity station, String day) {
        return SoilDaySummaryEntity.builder()
                .station(station)
                .day(day)
                .build();
    }
}
