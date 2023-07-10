package me.marcelberger.weatherapp.aggregator.service.aggregation.year.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.service.aggregation.year.YearAggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherYearAggregationServiceImpl extends YearAggregationService<WeatherSingleDataEntity, WeatherYearDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Override
    protected TargetBuilder<WeatherSingleDataEntity, WeatherYearDataEntity> createTargetBuilder() {
        return new WeatherYearTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected WeatherYearDataEntity createBaseTarget(StationEntity station, String year) {
        return WeatherYearDataEntity.builder()
                .station(station)
                .year(year)
                .build();
    }
}
