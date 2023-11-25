package me.marcelberger.weatherapp.core.facade.station;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;

public interface StationFacade {
    PageData<StationData> getAllStations();

    StationData getStationByCode(String stationCode);

    StationMediaFileData getStationMediaFileByNameAndStationCode(String mediaName, String stationCode);
}
