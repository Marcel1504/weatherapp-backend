package me.marcelberger.weatherapp.aggregator.service.aggregation.month;

import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.summary.month.MonthSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;

public abstract class MonthAggregationService<SOURCE, TARGET> extends AggregationService<SOURCE, TARGET> {
    @Autowired
    private MonthSummaryRepository<TARGET> monthSummaryRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.MONTH, CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusMonths(1);
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
    protected TARGET getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        String month = timestampBase.getValue(CalendarParameter.Item.MONTH);
        TARGET entity = monthSummaryRepository.findByStationAndMonthAndYear(station, month, year);
        if (entity == null) {
            entity = createBaseTarget(station, month, year);
        }
        return entity;
    }

    @Override
    protected void saveTarget(TARGET entity) {
        this.monthSummaryRepository.save(entity);
    }

    protected abstract TARGET createBaseTarget(StationEntity station, String month, String year);
}
