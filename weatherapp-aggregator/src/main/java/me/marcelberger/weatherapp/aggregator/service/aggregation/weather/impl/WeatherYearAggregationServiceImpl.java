package me.marcelberger.weatherapp.aggregator.service.aggregation.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.weather.WeatherAggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherYearRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherYearAggregationServiceImpl extends WeatherAggregationService<WeatherYearDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Autowired
    private WeatherYearRepository weatherYearRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusYears(1);
    }

    @Override
    protected TargetBuilder<WeatherDataEntity, WeatherYearDataEntity> createTargetBuilder() {
        return new WeatherYearTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected LocalDateTime getAggregationStartTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format("%s-01-01 00:00:00", timestampBase.getValues().get(CalendarParameter.Item.YEAR));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected LocalDateTime getAggregationEndTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format("%s-12-31 23:59:59", timestampBase.getValue(CalendarParameter.Item.YEAR));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected WeatherYearDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        WeatherYearDataEntity entity = weatherYearRepository.findByStationAndYear(station, year);
        if (entity == null) {
            entity = WeatherYearDataEntity.builder()
                    .year(year)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherYearDataEntity entity) {
        this.weatherYearRepository.save(entity);
    }
}
