package me.marcelberger.weatherapp.aggregator.service.aggregation.weather;

import me.marcelberger.weatherapp.aggregator.builder.TargetBuilder;
import me.marcelberger.weatherapp.aggregator.builder.weather.impl.WeatherDayTargetBuilderImpl;
import me.marcelberger.weatherapp.aggregator.parameter.CalendarParameter;
import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.day.DayDataRepository;
import me.marcelberger.weatherapp.core.service.weather.wind.WeatherWindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

@Service
public class WeatherDayAggregationServiceImpl extends AggregationService<WeatherDataEntity, WeatherDayDataEntity> {

    @Autowired
    private WeatherWindService weatherWindService;

    @Autowired
    private DayDataRepository<WeatherDayDataEntity> weatherDayRepository;

    @Override
    public Set<CalendarParameter.Item> getCalendarParameterItems() {
        return Set.of(CalendarParameter.Item.DAY);
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> incrementTimestampBase() {
        return base -> base.plusDays(1);
    }

    @Override
    protected TargetBuilder<WeatherDataEntity, WeatherDayDataEntity> createTargetBuilder() {
        return new WeatherDayTargetBuilderImpl(weatherWindService);
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
    protected WeatherDayDataEntity getOrCreateTarget(CalendarParameter timestampBase, StationEntity station) {
        String day = timestampBase.getValue(CalendarParameter.Item.DAY);
        WeatherDayDataEntity entity = weatherDayRepository.findByStationAndDay(station, day);
        if (entity == null) {
            entity = WeatherDayDataEntity.builder()
                    .day(day)
                    .station(station)
                    .build();
        }
        return entity;
    }

    @Override
    protected void saveTarget(WeatherDayDataEntity entity) {
        this.weatherDayRepository.save(entity);
    }
}
