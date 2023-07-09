package me.marcelberger.weatherapp.api.facade.station;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.station.StationData;

public interface StationFacade {
    PageData<StationData> getAll();

    StationData getByStationCode(String stationCode);
}
