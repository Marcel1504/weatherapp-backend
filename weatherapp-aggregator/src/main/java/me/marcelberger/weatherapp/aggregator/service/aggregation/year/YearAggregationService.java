package me.marcelberger.weatherapp.aggregator.service.aggregation.year;

import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.summary.year.YearSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

public abstract class YearAggregationService<SOURCE, TARGET> extends AggregationService<SOURCE, TARGET> {

    @Autowired
    private YearSummaryRepository<TARGET> yearSummaryRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusYears(1);
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
    protected TARGET getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        TARGET entity = yearSummaryRepository.findByStationAndYear(station, year);
        if (entity == null) {
            entity = createBaseTarget(station, year);
        }
        return entity;
    }

    @Override
    protected void saveTarget(TARGET entity) {
        this.yearSummaryRepository.save(entity);
    }

    protected abstract TARGET createBaseTarget(StationEntity station, String year);
}
