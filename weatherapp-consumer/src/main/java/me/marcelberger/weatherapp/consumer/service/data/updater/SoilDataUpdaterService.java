package me.marcelberger.weatherapp.consumer.service.data.updater;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class SoilDataUpdaterService<FROM> extends DataUpdaterService<FROM, SoilSingleSummaryEntity> {

    @Autowired
    private SingleSummaryRepository<SoilSingleSummaryEntity> soilRepository;

    @Override
    protected SoilSingleSummaryEntity getLatestExistingEntityForStation(StationEntity station) {
        return soilRepository.findLatestByStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(SoilSingleSummaryEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected SoilSingleSummaryEntity createNewEntity(FROM data, StationEntity station) {
        return SoilSingleSummaryEntity.builder()
                .station(station)
                .timestamp(getTimestamp(data))
                .temperature50cm(getTemperature50cm(data))
                .temperature100cm(getTemperature100cm(data))
                .temperature200cm(getTemperature200cm(data))
                .build();
    }

    @Override
    protected void saveEntity(SoilSingleSummaryEntity entity) {
        soilRepository.save(entity);
    }

    protected abstract LocalDateTime getTimestamp(FROM data);

    protected abstract Double getTemperature50cm(FROM data);

    protected abstract Double getTemperature100cm(FROM data);

    protected abstract Double getTemperature200cm(FROM data);
}
