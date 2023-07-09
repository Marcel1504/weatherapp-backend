package me.marcelberger.weatherapp.api.facade.station;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.station.StationData;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationFacadeImpl implements StationFacade {

    @Autowired
    private MessageService msg;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private Mapper<StationEntity, StationData> stationDataMapper;

    @Override
    public PageData<StationData> getAll() {
        return stationDataMapper.mapList(stationRepository.findAll());
    }

    @Override
    public StationData getByStationCode(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        return stationDataMapper.map(station);
    }
}
