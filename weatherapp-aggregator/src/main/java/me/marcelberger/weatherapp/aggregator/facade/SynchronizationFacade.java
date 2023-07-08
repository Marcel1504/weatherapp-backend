package me.marcelberger.weatherapp.aggregator.facade;

import me.marcelberger.weatherapp.core.data.StatusData;

public interface SynchronizationFacade {
    StatusData syncFullForStationByCode(String stationCode);

    StatusData syncFullForAllStations();

    StatusData syncDeltaForStationByCode(String stationCode);

    StatusData syncDeltaForAllStations();
}
