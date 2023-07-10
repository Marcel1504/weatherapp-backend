package me.marcelberger.weatherapp.aggregator.service.aggregation.month.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.month.MonthAggregationService;
import me.marcelberger.weatherapp.core.entity.data.month.SoilMonthDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilMonthAggregationServiceImpl extends MonthAggregationService<SoilSingleDataEntity, SoilMonthDataEntity> {

    @Override
    protected TargetBuilder<SoilSingleDataEntity, SoilMonthDataEntity> createTargetBuilder() {
        return new SoilMonthTargetBuilderImpl();
    }

    @Override
    protected SoilMonthDataEntity createBaseTarget(StationEntity station, String month, String year) {
        return SoilMonthDataEntity.builder()
                .station(station)
                .month(month)
                .year(year)
                .build();
    }
}
