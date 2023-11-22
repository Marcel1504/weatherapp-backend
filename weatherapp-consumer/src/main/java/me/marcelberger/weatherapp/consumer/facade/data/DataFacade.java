package me.marcelberger.weatherapp.consumer.facade.data;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Slf4j
public abstract class DataFacade<DATA> {

    @Autowired
    private StationRepository stationRepository;

    public String updateWithStationCodeFromPrincipalName(Principal principal, DATA data) {
        StationEntity station = stationRepository.findByCode(principal.getName());
        if (station == null || station.getType() != stationType()) {
            return String.format("Configuration for station '%s' does not exist, skipping update", principal.getName());
        }
        update(station, data);
        return successMessage(station.getCode());
    }

    public String updateWithStationFromData(DATA data) {
        StationEntity station = getStationFromData(data);
        if (station == null || station.getType() != stationType()) {
            return "Configuration for station loaded from data does not exist, skipping update";
        }
        update(station, data);
        return successMessage(station.getCode());
    }

    protected abstract StationEntity getStationFromData(DATA data);

    protected abstract void update(StationEntity station, DATA data);

    protected abstract StationTypeEnum stationType();

    private String successMessage(String stationCode) {
        return String.format("Started updating data for station '%s'", stationCode);
    }
}
