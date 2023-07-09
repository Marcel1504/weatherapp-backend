package me.marcelberger.weatherapp.api.facade.station.media;

import me.marcelberger.weatherapp.api.dto.station.StationMediaData;

public interface StationMediaFacade {
    StationMediaData getStationMedia(String mediaName, String stationCode);
}
