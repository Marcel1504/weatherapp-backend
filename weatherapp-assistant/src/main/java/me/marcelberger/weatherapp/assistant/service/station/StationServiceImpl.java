package me.marcelberger.weatherapp.assistant.service.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public StationEntity getStationById(Long id) {
        StationEntity station = null;
        if (id != null) {
            station = stationRepository.findById(id).orElse(null);
        }
        if (station == null) {
            throw new ServiceException("Could not find StationEntity(id=%s)", id);
        }
        return station;
    }
}
