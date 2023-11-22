package me.marcelberger.weatherapp.aggregator.service.aggregation.hour;

import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.summary.hour.HourSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

public abstract class HourAggregationService<SOURCE, TARGET> extends AggregationService<SOURCE, TARGET> {

    @Autowired
    private HourSummaryRepository<TARGET> hourSummaryRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY, CalendarParameter.Item.HOUR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusHours(1);
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
    protected TARGET getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        String hour = timestampBase.getValue(CalendarParameter.Item.HOUR);
        TARGET entity = hourSummaryRepository.findByStationAndDayAndHour(station, day, hour);
        if (entity == null) {
            entity = createBaseTarget(station, day, hour);
        }
        return entity;
    }

    @Override
    protected void saveTarget(TARGET entity) {
        this.hourSummaryRepository.save(entity);
    }

    protected abstract TARGET createBaseTarget(StationEntity station, String day, String hour);
}
