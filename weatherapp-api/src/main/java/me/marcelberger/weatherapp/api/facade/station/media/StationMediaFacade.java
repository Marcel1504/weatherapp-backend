package me.marcelberger.weatherapp.api.facade.station.media;

import me.marcelberger.weatherapp.api.data.station.StationMediaData;

public interface StationMediaFacade {
    StationMediaData getStationMedia(String mediaName, String stationCode);
}
