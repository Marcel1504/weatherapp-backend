package me.marcelberger.weatherapp.syncer.service.aggregation.weather.impl;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherMonthEntity;
import me.marcelberger.weatherapp.core.repository.weather.summary.WeatherMonthRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import me.marcelberger.weatherapp.syncer.builder.TargetBuilder;
import me.marcelberger.weatherapp.syncer.builder.weather.impl.WeatherMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.syncer.parameter.CalendarParameter;
import me.marcelberger.weatherapp.syncer.service.aggregation.weather.WeatherAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherMonthAggregationServiceImpl extends WeatherAggregationService<WeatherMonthEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Autowired
    private WeatherMonthRepository weatherMonthRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.MONTH, CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusMonths(1);
    }

    @Override
    protected TargetBuilder<WeatherEntity, WeatherMonthEntity> createTargetBuilder() {
        return new WeatherMonthTargetBuilderImpl(weatherWindService);
    }

    @Override
    protected LocalDateTime getAggregationStartTimestamp(CalendarParameter timestampBase) {
        String timestamp = String.format(
                "%s-%s-01 00:00:00",
                timestampBase.getValue(CalendarParameter.Item.YEAR),
                timestampBase.getValue(CalendarParameter.Item.MONTH));
        return LocalDateTime.parse(timestamp, super.formatter);
    }

    @Override
    protected LocalDateTime getAggregationEndTimestamp(CalendarParameter timestampBase) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        String month = timestampBase.getValue(CalendarParameter.Item.MONTH);
        YearMonth yearMonth = YearMonth.parse(String.format("%s-%s-01", year, month), DateTimeFormatter.ISO_DATE);
        return yearMonth.atEndOfMonth().atTime(23, 59, 59);
    }

    @Override
    protected WeatherMonthEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        String month = timestampBase.getValue(CalendarParameter.Item.MONTH);
        WeatherMonthEntity entity = weatherMonthRepository.findByStationAndMonthAndYear(station, month, year);
        if (entity == null) {
            entity = WeatherMonthEntity.builder()
                    .month(month)
                    .year(year)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherMonthEntity entity) {
        this.weatherMonthRepository.save(entity);
    }
}
