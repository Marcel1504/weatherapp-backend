package me.marcelberger.weatherapp.consumer.service.data.updater;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.service.data.validator.DataValidatorService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public abstract class DataUpdaterService<FROM, TO> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private DataValidatorService<TO> dataValidatorService;

    @Async
    public void update(StationEntity station, FROM request) {
        TO newEntity = createNewEntity(request, station);
        LocalDateTime newEntityTimestamp = getTimestampOfEntity(newEntity);
        LocalDateTime latestEntityTimestamp = getTimestampOfEntity(getLatestExistingEntityForStation(station));
        if (station.getDisabled()) {
            log.warn(
                    "Update for station '{}' failed: station is disabled",
                    station.getCode());
            return;
        }
        if (latestEntityTimestamp != null
                && (latestEntityTimestamp.isAfter(newEntityTimestamp)
                || latestEntityTimestamp.isEqual(newEntityTimestamp))) {
            log.warn(
                    "Update for station '{}' failed: new data is not newer than latest existing data",
                    station.getCode());
            return;
        }
        if (dataValidatorService.isValid(newEntity)) {
            saveEntity(newEntity);
            log.info("Update for station '{}' successful, new data is at timestamp {}",
                    station.getCode(),
                    newEntityTimestamp.format(DateTimeFormatter.ISO_DATE_TIME));
        } else {
            log.warn("Update for station '{}' failed: data is invalid", station.getCode());
        }

        station.setLastActivity(LocalDateTime.now());
        stationRepository.save(station);
    }

    protected abstract TO getLatestExistingEntityForStation(StationEntity station);

    protected abstract LocalDateTime getTimestampOfEntity(TO entity);

    protected abstract TO createNewEntity(FROM request, StationEntity station);

    protected abstract void saveEntity(TO entity);
}
