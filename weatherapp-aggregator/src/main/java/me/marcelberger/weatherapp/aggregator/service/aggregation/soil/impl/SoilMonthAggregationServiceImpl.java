package me.marcelberger.weatherapp.aggregator.service.aggregation.soil.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilMonthTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.soil.SoilAggregationService;
import me.marcelberger.weatherapp.core.entity.data.month.SoilMonthDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.soil.summary.SoilMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;

@Service
public class SoilMonthAggregationServiceImpl extends SoilAggregationService<SoilMonthDataEntity> {

    @Autowired
    private SoilMonthRepository soilMonthRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.MONTH, CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusMonths(1);
    }

    @Override
    protected TargetBuilder<SoilDataEntity, SoilMonthDataEntity> createTargetBuilder() {
        return new SoilMonthTargetBuilderImpl();
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
    protected SoilMonthDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        String month = timestampBase.getValue(CalendarParameter.Item.MONTH);
        SoilMonthDataEntity entity = soilMonthRepository.findByStationAndMonthAndYear(station, month, year);
        if (entity == null) {
            entity = SoilMonthDataEntity.builder()
                    .month(month)
                    .year(year)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(SoilMonthDataEntity entity) {
        this.soilMonthRepository.save(entity);
    }
}
