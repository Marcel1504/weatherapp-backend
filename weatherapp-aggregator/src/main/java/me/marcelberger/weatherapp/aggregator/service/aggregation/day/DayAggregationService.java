package me.marcelberger.weatherapp.aggregator.service.aggregation.day;

import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

public abstract class DayAggregationService<SOURCE, TARGET> extends AggregationService<SOURCE, TARGET> {

    @Autowired
    private DaySummaryRepository<TARGET> daySummaryRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusDays(1);
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
    protected TARGET getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        TARGET entity = daySummaryRepository.findByStationAndDay(station, day);
        if (entity == null) {
            entity = createBaseTarget(station, day);
        }
        return entity;
    }

    @Override
    protected void saveTarget(TARGET entity) {
        daySummaryRepository.save(entity);
    }

    protected abstract TARGET createBaseTarget(StationEntity station, String day);
}
