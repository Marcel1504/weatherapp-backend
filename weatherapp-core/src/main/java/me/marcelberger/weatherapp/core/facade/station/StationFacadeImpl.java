package me.marcelberger.weatherapp.core.facade.station;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationFacadeImpl implements StationFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<StationEntity, StationData> stationMapper;

    @Override
    public PageData<StationData> getAll() {
        return stationMapper.mapList(stationRepository.findAll());
    }

    @Override
    public StationData getByStationCode(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        return stationMapper.map(station);
    }
}
