package me.marcelberger.weatherapp.api.facade.station.media;

import me.marcelberger.weatherapp.api.data.station.StationMediaData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StationMediaFacadeImpl implements StationMediaFacade {

    @Autowired
    private MessageService msg;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private DataMapper<StationMediaEntity, StationMediaData> stationMediaDataMapper;

    @Override
    public StationMediaData getStationMedia(String name, String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new ServiceException(msg.get("station.notFound", stationCode));
        }
        StationMediaEntity stationMedia = stationMediaRepository.findByNameAndStation(name, station);
        if (stationMedia == null) {
            throw new ServiceException(msg.get("station.media.notFound", name));
        }
        return stationMediaDataMapper.map(stationMedia);
    }
}
