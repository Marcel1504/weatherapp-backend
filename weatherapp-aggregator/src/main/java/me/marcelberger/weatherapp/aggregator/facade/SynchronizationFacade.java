package me.marcelberger.weatherapp.aggregator.facade;

public interface SynchronizationFacade {
    String syncFullForStationByCode(String stationCode);

    String syncFullForAllStations();

    String syncDeltaForStationByCode(String stationCode);

    String syncDeltaForAllStations();
}
