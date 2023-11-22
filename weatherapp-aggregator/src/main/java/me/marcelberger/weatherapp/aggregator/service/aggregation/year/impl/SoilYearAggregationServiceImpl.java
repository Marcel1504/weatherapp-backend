package me.marcelberger.weatherapp.aggregator.service.aggregation.year.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.year.YearAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.year.SoilYearSummaryEntity;
import org.springframework.stereotype.Service;


@Service
public class SoilYearAggregationServiceImpl extends YearAggregationService<SoilSingleSummaryEntity, SoilYearSummaryEntity> {

    @Override
    protected TargetBuilder<SoilSingleSummaryEntity, SoilYearSummaryEntity> createTargetBuilder() {
        return new SoilYearTargetBuilderImpl();
    }

    @Override
    protected SoilYearSummaryEntity createBaseTarget(StationEntity station, String year) {
        return SoilYearSummaryEntity.builder()
                .station(station)
                .year(year)
                .build();
    }
}
