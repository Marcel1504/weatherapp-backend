package me.marcelberger.weatherapp.aggregator.service.aggregation.month.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.month.MonthAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherMonthAggregationServiceImpl extends MonthAggregationService<WeatherSingleSummaryEntity, WeatherMonthSummaryEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleSummaryEntity, WeatherMonthSummaryEntity> createTargetBuilder() {
        return new WeatherMonthTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherMonthSummaryEntity createBaseTarget(StationEntity station, String month, String year) {
        return WeatherMonthSummaryEntity.builder()
                .station(station)
                .month(month)
                .year(year)
                .build();
    }
}
