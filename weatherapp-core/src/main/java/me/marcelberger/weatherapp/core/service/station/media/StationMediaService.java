package me.marcelberger.weatherapp.core.service.station.media;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;

public interface StationMediaService {
    StationMediaEntity getStationMediaByNameAndStation(String mediaName, StationEntity station);
}
