package me.marcelberger.weatherapp.core.facade.station.media;

import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;

public interface StationMediaFacade {
    StationMediaFileData getStationMediaFile(String mediaName, String stationCode);
}
