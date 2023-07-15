package me.marcelberger.weatherapp.api.facade.station.media;

import me.marcelberger.weatherapp.api.dto.station.StationMediaFileData;

public interface StationMediaFacade {
    StationMediaFileData getStationMediaFile(String mediaName, String stationCode);
}
