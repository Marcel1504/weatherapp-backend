package me.marcelberger.weatherapp.aggregator.service.aggregation.year.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.year.YearAggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.stereotype.Service;


@Service
public class SoilYearAggregationServiceImpl extends YearAggregationService<SoilSingleDataEntity, SoilYearDataEntity> {

    @Override
    protected TargetBuilder<SoilSingleDataEntity, SoilYearDataEntity> createTargetBuilder() {
        return new SoilYearTargetBuilderImpl();
    }

    @Override
    protected SoilYearDataEntity createBaseTarget(StationEntity station, String year) {
        return SoilYearDataEntity.builder()
                .station(station)
                .year(year)
                .build();
    }
}
