package me.marcelberger.weatherapp.syncer.service.synchronization;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationParameterEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import me.marcelberger.weatherapp.core.repository.station.StationParameterRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.syncer.parameter.CalendarParameter;
import me.marcelberger.weatherapp.syncer.service.aggregation.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Slf4j
public abstract class SynchronizationService<SOURCE> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationParameterRepository stationParameterRepository;

    @Autowired
    private List<AggregationService<SOURCE, ?>> aggregationServices;

    @Value("${weatherapp.station.parameter.lastSyncedEntityTimestamp}")
    private String lastSyncedEntityTimestampParameter;

    @Value("${weatherapp.synchronization.startFromYear}")
    private String startFromYear;

    public void syncForStation(StationEntity station, boolean syncDelta) {
        LocalDateTime start = getSyncStartTimestamp(syncDelta, station);
        LocalDateTime end = getLatestSourceTimestamp(station);
        aggregationServices.forEach(s -> sync(station, start, end, s));
        saveLastSyncStationParameter(station, end);
    }

    public abstract StationTypeEnum getStationType();

    protected abstract LocalDateTime getLatestSourceTimestamp(StationEntity station);

    private void sync(StationEntity station,
                      LocalDateTime start,
                      LocalDateTime end,
                      AggregationService<?, ?> service) {
        if (service == null || (start.equals(end))) return;
        Set<CalendarParameter.Item> calParamItems = service.getCalendarParameterItems();
        LocalDateTime current = LocalDateTime.from(start);
        CalendarParameter calParamCurrent = CalendarParameter.from(current, calParamItems);
        CalendarParameter calParamEnd = CalendarParameter.from(end, calParamItems);
        boolean run = true;
        while (run) {
            service.aggregate(calParamCurrent, station);
            log.info("Synced station {} for {}",
                    station.getCode(),
                    calParamCurrent);
            if (calParamCurrent.equals(calParamEnd)) {
                run = false;
            } else {
                current = service.incrementTimestampBase().apply(current);
                calParamCurrent = CalendarParameter.from(current, calParamItems);
            }
        }
    }

    private LocalDateTime getSyncStartTimestamp(boolean syncDelta, StationEntity station) {
        String timestampString;
        StationParameterEntity parameter = stationParameterRepository
                .findByStationAndCode(station, lastSyncedEntityTimestampParameter);
        if (syncDelta && parameter != null && parameter.getValue() != null) {
            timestampString = parameter.getValue();
        } else {
            timestampString = String.format("%s-01-01 00:00:00", startFromYear);
        }
        return LocalDateTime.parse(timestampString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void saveLastSyncStationParameter(StationEntity station, LocalDateTime end) {
        StationParameterEntity parameter = stationParameterRepository
                .findByStationAndCode(station, lastSyncedEntityTimestampParameter);
        if (parameter == null) {
            parameter = StationParameterEntity.builder()
                    .station(station)
                    .code(lastSyncedEntityTimestampParameter)
                    .build();
        }
        parameter.setValue(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        stationParameterRepository.save(parameter);
    }
}
