package me.marcelberger.weatherapp.aggregator.service.aggregation.soil.impl;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.soil.SoilAggregationService;
import me.marcelberger.weatherapp.core.entity.soil.SoilEntity;
import me.marcelberger.weatherapp.core.entity.soil.summary.SoilDayEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.soil.summary.SoilDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class SoilDayAggregationServiceImpl extends SoilAggregationService<SoilDayEntity> {

    @Autowired
    private SoilDayRepository soilDayRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusDays(1);
    }

    @Override
    protected TargetBuilder<SoilEntity, SoilDayEntity> createTargetBuilder() {
        return new SoilDayTargetBuilderImpl();
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
    protected SoilDayEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        SoilDayEntity entity = soilDayRepository.findByStationAndDay(station, day);
        if (entity == null) {
            entity = SoilDayEntity.builder()
                    .day(day)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(SoilDayEntity entity) {
        soilDayRepository.save(entity);
    }
}
