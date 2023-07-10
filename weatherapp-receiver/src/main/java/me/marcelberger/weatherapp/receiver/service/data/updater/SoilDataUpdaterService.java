package me.marcelberger.weatherapp.receiver.service.data.updater;

import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class SoilDataUpdaterService<FROM> extends DataUpdaterService<FROM, SoilSingleDataEntity> {

    @Autowired
    private SingleDataRepository<SoilSingleDataEntity> soilRepository;

    @Override
    protected SoilSingleDataEntity getLatestExistingEntityForStation(StationEntity station) {
        return soilRepository.findLatestByStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(SoilSingleDataEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected SoilSingleDataEntity createNewEntity(FROM data, StationEntity station) {
        return SoilSingleDataEntity.builder()
                .station(station)
                .timestamp(getTimestamp(data))
                .temperature50cm(getTemperature50cm(data))
                .temperature100cm(getTemperature100cm(data))
                .temperature200cm(getTemperature200cm(data))
                .build();
    }

    @Override
    protected void saveEntity(SoilSingleDataEntity entity) {
        soilRepository.save(entity);
    }

    protected abstract LocalDateTime getTimestamp(FROM data);

    protected abstract Double getTemperature50cm(FROM data);

    protected abstract Double getTemperature100cm(FROM data);

    protected abstract Double getTemperature200cm(FROM data);
}
