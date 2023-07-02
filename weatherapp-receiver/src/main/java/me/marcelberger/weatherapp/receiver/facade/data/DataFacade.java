package me.marcelberger.weatherapp.receiver.facade.data;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.core.entity.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Slf4j
public abstract class DataFacade<DATA> {

    @Autowired
    private StationRepository stationRepository;

    public StatusData updateWithStationCodeFromPrincipalName(Principal principal, DATA data) {
        StationEntity station = stationRepository.findByCode(principal.getName());
        if (station == null) {
            throw new ServiceException(
                    "Configuration for station '%s' does not exist, skipping update",
                    principal.getName());
        }
        update(station, data);
        return successStatusData(station.getCode());
    }

    public StatusData updateWithStationFromData(DATA data) {
        StationEntity station = getStationFromData(data);
        if (station == null) {
            throw new ServiceException(
                    "Configuration for station loaded from data does not exist, skipping update");
        }
        update(station, data);
        return successStatusData(station.getCode());
    }

    protected abstract StationEntity getStationFromData(DATA data);

    protected abstract void update(StationEntity station, DATA data);

    private StatusData successStatusData(String stationCode) {
        return StatusData.builder()
                .message(String.format(
                        "Started updating data for station '%s'",
                        stationCode))
                .build();
    }
}
