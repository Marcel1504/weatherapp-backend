package me.marcelberger.weatherapp.aggregator.service.aggregation.soil.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilYearTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.soil.SoilAggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.soil.summary.SoilYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class SoilYearAggregationServiceImpl extends SoilAggregationService<SoilYearDataEntity> {

    @Autowired
    private SoilYearRepository soilYearRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.YEAR);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusYears(1);
    }

    @Override
    protected TargetBuilder<SoilDataEntity, SoilYearDataEntity> createTargetBuilder() {
        return new SoilYearTargetBuilderImpl();
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
    protected SoilYearDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String year = timestampBase.getValue(CalendarParameter.Item.YEAR);
        SoilYearDataEntity entity = soilYearRepository.findByStationAndYear(station, year);
        if (entity == null) {
            entity = SoilYearDataEntity.builder()
                    .year(year)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(SoilYearDataEntity entity) {
        this.soilYearRepository.save(entity);
    }
}
