package me.marcelberger.weatherapp.api.facade.data.single;


import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SingleDataFacade<SOURCE, TARGET> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService msg;

    @Autowired
    private Mapper<SOURCE, TARGET> dataMapper;

    @Autowired
    private SingleDataRepository<SOURCE> singleDataRepository;

    public TARGET getLatest(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        SOURCE data = singleDataRepository.findLatestByStationId(station.getId());
        if (data == null) {
            throw new ServiceException(msg.get(getLatestDataNotFoundMessageKey()));
        }
        return dataMapper.map(data);
    }

    protected abstract String getLatestDataNotFoundMessageKey();
}
