package me.marcelberger.weatherapp.core.facade.station;


import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;

public interface StationFacade {
    PageData<StationData> getAll();

    StationData getByStationCode(String stationCode);
}
