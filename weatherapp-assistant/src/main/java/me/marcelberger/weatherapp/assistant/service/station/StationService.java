package me.marcelberger.weatherapp.assistant.service.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;

public interface StationService {
    StationEntity getStationById(Long id);
}
