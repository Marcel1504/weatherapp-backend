package me.marcelberger.weatherapp.aggregator.service.aggregation.year.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.year.YearAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherYearAggregationServiceImpl extends YearAggregationService<WeatherSingleSummaryEntity, WeatherYearSummaryEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleSummaryEntity, WeatherYearSummaryEntity> createTargetBuilder() {
        return new WeatherYearTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherYearSummaryEntity createBaseTarget(StationEntity station, String year) {
        return WeatherYearSummaryEntity.builder()
                .station(station)
                .year(year)
                .build();
    }
}
