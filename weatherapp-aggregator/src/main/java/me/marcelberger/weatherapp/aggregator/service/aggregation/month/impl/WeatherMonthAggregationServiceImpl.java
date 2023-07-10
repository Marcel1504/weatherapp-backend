package me.marcelberger.weatherapp.aggregator.service.aggregation.month.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.month.MonthAggregationService;
import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherMonthAggregationServiceImpl extends MonthAggregationService<WeatherSingleDataEntity, WeatherMonthDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleDataEntity, WeatherMonthDataEntity> createTargetBuilder() {
        return new WeatherMonthTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherMonthDataEntity createBaseTarget(StationEntity station, String month, String year) {
        return WeatherMonthDataEntity.builder()
                .station(station)
                .month(month)
                .year(year)
                .build();
    }
}
