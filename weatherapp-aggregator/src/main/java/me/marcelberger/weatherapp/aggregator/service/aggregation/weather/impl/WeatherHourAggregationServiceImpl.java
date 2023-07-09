package me.marcelberger.weatherapp.aggregator.service.aggregation.weather.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherHourTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.weather.WeatherAggregationService;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherHourRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherHourAggregationServiceImpl extends WeatherAggregationService<WeatherHourDataEntity> {

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
    protected TargetBuilder<WeatherDataEntity, WeatherHourDataEntity> createTargetBuilder() {
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
    protected WeatherHourDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        String hour = timestampBase.getValue(CalendarParameter.Item.HOUR);
        WeatherHourDataEntity entity = weatherHourRepository.findByStationAndDayAndHour(station, day, hour);
        if (entity == null) {
            entity = WeatherHourDataEntity.builder()
                    .day(day)
                    .hour(hour)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherHourDataEntity entity) {
        this.weatherHourRepository.save(entity);
    }
}
