package me.marcelberger.weatherapp.aggregator.service.aggregation.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.weather.WeatherAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherDayEntity;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherDayRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherDayAggregationServiceImpl extends WeatherAggregationService<WeatherDayEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Autowired
    private WeatherDayRepository weatherDayRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusDays(1);
    }

    @Override
    protected TargetBuilder<WeatherEntity, WeatherDayEntity> createTargetBuilder() {
        return new WeatherDayTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected LocalDateTime getAggregationStartTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format("%s 00:00:00", timestampBase.getValue(CalendarParameter.Item.DAY));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected LocalDateTime getAggregationEndTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format("%s 23:59:59", timestampBase.getValue(CalendarParameter.Item.DAY));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected WeatherDayEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        WeatherDayEntity entity = weatherDayRepository.findByStationAndDay(station, day);
        if (entity == null) {
            entity = WeatherDayEntity.builder()
                    .day(day)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherDayEntity entity) {
        this.weatherDayRepository.save(entity);
    }
}
