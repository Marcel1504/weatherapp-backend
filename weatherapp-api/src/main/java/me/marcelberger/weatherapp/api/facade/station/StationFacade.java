package me.marcelberger.weatherapp.api.facade.station;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.station.StationData;

public interface StationFacade {
    PageData<StationData> getAll();

    StationData getByStationCode(String stationCode);
}
