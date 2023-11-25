package me.marcelberger.weatherapp.core.facade.station;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationFacadeImpl implements StationFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<StationEntity, StationData> stationMapper;

    @Override
    public PageData<StationData> getAll() {
        return stationMapper.mapToPage(stationRepository.findAll());
    }

    @Override
    public StationData getByStationCode(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return stationMapper.map(station);
    }

    @Override
    public StationData getById(Long id) {
        StationEntity station = null;
        if (id != null) {
            station = stationRepository.findById(id).orElse(null);
        }
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00024, String.valueOf(id));
        }
        return stationMapper.map(station);
    }

    @Override
    public StationData getByIdOrNull(Long id) {
        StationEntity station = null;
        if (id != null) {
            station = stationRepository.findById(id).orElse(null);
        }
        return station != null ? stationMapper.map(station) : null;
    }

    @Override
    public StationData searchClosestStationMatchByName(String nameSearchQuery) {
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
            throw new CoreException(ErrorCodeEnum.CODE00025, nameSearchQuery);
        }
        return stationMapper.map(match);
    }
}
