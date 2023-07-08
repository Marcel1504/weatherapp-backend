package me.marcelberger.weatherapp.aggregator.service.aggregation.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherHourTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.weather.WeatherAggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherHourEntity;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherHourRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherHourAggregationServiceImpl extends WeatherAggregationService<WeatherHourEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Autowired
    private WeatherHourRepository weatherHourRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY, CalendarParameter.Item.HOUR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusHours(1);
    }

    @Override
    protected TargetBuilder<WeatherEntity, WeatherHourEntity> createTargetBuilder() {
        return new WeatherHourTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected LocalDateTime getAggregationStartTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format(
                "%s %s:00:00",
                timestampBase.getValue(CalendarParameter.Item.DAY),
                timestampBase.getValue(CalendarParameter.Item.HOUR));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected LocalDateTime getAggregationEndTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format(
                "%s %s:59:59",
                timestampBase.getValue(CalendarParameter.Item.DAY),
                timestampBase.getValue(CalendarParameter.Item.HOUR));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected WeatherHourEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        String hour = timestampBase.getValue(CalendarParameter.Item.HOUR);
        WeatherHourEntity entity = weatherHourRepository.findByStationAndDayAndHour(station, day, hour);
        if (entity == null) {
            entity = WeatherHourEntity.builder()
                    .day(day)
                    .hour(hour)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherHourEntity entity) {
        this.weatherHourRepository.save(entity);
    }
}
