package me.marcelberger.weatherapp.core.repository.data.single;

import me.marcelberger.weatherapp.core.entity.data.DataEntity;

public interface SingleDataRepository<ENTITY extends DataEntity> {
    ENTITY findLatestByStationId(Long stationId);
}
