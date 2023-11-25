package me.marcelberger.weatherapp.core.service.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<StationEntity> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public StationEntity getByStationCode(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreError(CoreError.Code.CORE00200, "Station(code=%s) not found", stationCode);
        }
        return station;
    }

    @Override
    public StationEntity getById(Long id) {
        StationEntity station = null;
        if (id != null) {
            station = stationRepository.findById(id).orElse(null);
        }
        if (station == null) {
            throw new CoreError(CoreError.Code.CORE00200, "Station(id=%s) not found", String.valueOf(id));
        }
        return station;
    }

    @Override
    public StationEntity getByAPIKey(String apiKey) {
        StationEntity station = stationRepository.findByApiKey(apiKey);
        if (station == null) {
            throw new CoreError(CoreError.Code.CORE00200, "Station(key=%s) not found", apiKey);
        }
        return station;
    }

    @Override
    public StationEntity getByIdOrNull(Long id) {
        StationEntity station = null;
        if (id != null) {
            station = stationRepository.findById(id).orElse(null);
        }
        return station;
    }

    @Override
    public StationEntity searchClosestStationMatchByName(String nameSearchQuery) {
        if (nameSearchQuery == null) {
            nameSearchQuery = "";
        }
        List<StationEntity> allStations = stationRepository.findAll();
        StationEntity match = null;
        Integer currentClosestDistance = Integer.MAX_VALUE;
        for (StationEntity station : allStations.stream().filter(s -> s.getName() != null).toList()) {
            Integer distance = new LevenshteinDistance().apply(nameSearchQuery, station.getName());
            if (distance < currentClosestDistance) {
                currentClosestDistance = distance;
                match = station;
            }
        }
        if (match == null) {
            throw new CoreError(CoreError.Code.CORE00200, "Station(nameSearch=%s) not found");
        }
        return match;
    }
}
