package me.marcelberger.weatherapp.aggregator.service.aggregation.month.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.month.MonthAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.month.SoilMonthSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilMonthAggregationServiceImpl extends MonthAggregationService<SoilSingleSummaryEntity, SoilMonthSummaryEntity> {

    @Override
    protected TargetBuilder<SoilSingleSummaryEntity, SoilMonthSummaryEntity> createTargetBuilder() {
        return new SoilMonthTargetBuilderImpl();
    }

    @Override
    protected SoilMonthSummaryEntity createBaseTarget(StationEntity station, String month, String year) {
        return SoilMonthSummaryEntity.builder()
                .station(station)
                .month(month)
                .year(year)
                .build();
    }
}
