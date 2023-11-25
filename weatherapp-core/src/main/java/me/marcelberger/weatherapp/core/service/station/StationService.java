package me.marcelberger.weatherapp.core.service.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;

import java.util.List;

public interface StationService {
    List<StationEntity> getAll();

    StationEntity getByStationCode(String stationCode);

    StationEntity getById(Long id);

    StationEntity getByAPIKey(String apiKey);

    StationEntity getByIdOrNull(Long id);

    StationEntity searchClosestStationMatchByName(String nameSearchQuery);
}
