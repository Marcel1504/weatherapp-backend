package me.marcelberger.weatherapp.receiver.service.data.updater;

import me.marcelberger.weatherapp.core.entity.SoilEntity;
import me.marcelberger.weatherapp.core.entity.StationEntity;
import me.marcelberger.weatherapp.core.repository.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class SoilDataUpdaterService<FROM> extends DataUpdaterService<FROM, SoilEntity> {

    @Autowired
    private SoilRepository soilRepository;

    @Override
    protected SoilEntity getLatestExistingEntityForStation(StationEntity station) {
        return soilRepository.findLatestByStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(SoilEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected SoilEntity createNewEntity(FROM data, StationEntity station) {
        return SoilEntity.builder()
                .station(station)
                .timestamp(getTimestamp(data))
                .temperature50cm(getTemperature50cm(data))
                .temperature100cm(getTemperature100cm(data))
                .temperature200cm(getTemperature200cm(data))
                .build();
    }

    @Override
    protected void saveEntity(SoilEntity entity) {
        soilRepository.save(entity);
    }

    protected abstract LocalDateTime getTimestamp(FROM data);

    protected abstract Double getTemperature50cm(FROM data);

    protected abstract Double getTemperature100cm(FROM data);

    protected abstract Double getTemperature200cm(FROM data);
}
