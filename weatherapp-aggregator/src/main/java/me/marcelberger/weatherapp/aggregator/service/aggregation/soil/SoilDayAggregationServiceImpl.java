package me.marcelberger.weatherapp.aggregator.service.aggregation.soil;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.soil.impl.SoilDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.day.DayDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class SoilDayAggregationServiceImpl extends AggregationService<SoilDataEntity, SoilDayDataEntity> {

    @Autowired
    private DayDataRepository<SoilDayDataEntity> dayDataRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusDays(1);
    }

    @Override
    protected TargetBuilder<SoilDataEntity, SoilDayDataEntity> createTargetBuilder() {
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
    protected SoilDayDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        SoilDayDataEntity entity = dayDataRepository.findByStationAndDay(station, day);
        if (entity == null) {
            entity = SoilDayDataEntity.builder()
                    .day(day)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(SoilDayDataEntity entity) {
        dayDataRepository.save(entity);
    }
}
