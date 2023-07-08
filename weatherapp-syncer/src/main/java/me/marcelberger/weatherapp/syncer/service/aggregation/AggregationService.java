package me.marcelberger.weatherapp.syncer.service.aggregation;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.syncer.builder.TargetBuilder;
import me.marcelberger.weatherapp.syncer.parameter.CalendarParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Function;

public abstract class AggregationService<SOURCE, TARGET> {

    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void aggregate(CalendarParameter timestampBase, StationEntity station) {
        boolean hasMore = true;
        int currentPage = 0;
        TargetBuilder<SOURCE, TARGET> builder = createTargetBuilder();
        LocalDateTime start = getAggregationStartTimestamp(timestampBase);
        LocalDateTime end = getAggregationEndTimestamp(timestampBase);
        while (hasMore) {
            Page<SOURCE> list = loadSourceEntities(PageRequest.of(
                            currentPage, 1000),
                    station,
                    start.format(formatter),
                    end.format(formatter));
            if (list.hasContent()) {
                builder.pushSourceEntities(list.getContent());
            }
            currentPage++;
            hasMore = list.hasNext();
        }
        if (builder.hasContent()) {
            saveTarget(builder.build(getOrCreateTarget(timestampBase, station)));
        }
    }

    public abstract Set<CalendarParameter.Item> getCalendarParameterItems();

    public abstract Function<LocalDateTime, LocalDateTime> incrementTimestampBase();

    protected abstract TargetBuilder<SOURCE, TARGET> createTargetBuilder();

    protected abstract LocalDateTime getAggregationStartTimestamp(CalendarParameter timestampBase);

    protected abstract LocalDateTime getAggregationEndTimestamp(CalendarParameter timestampBase);

    protected abstract TARGET getOrCreateTarget(CalendarParameter timestampBase, StationEntity station);

    protected abstract void saveTarget(TARGET entity);

    protected abstract Page<SOURCE> loadSourceEntities(
            PageRequest request,
            StationEntity station,
            String startTimestamp,
            String endTimestamp);
}
