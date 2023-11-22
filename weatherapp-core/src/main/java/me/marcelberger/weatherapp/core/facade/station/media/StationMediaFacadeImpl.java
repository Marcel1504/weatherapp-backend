package me.marcelberger.weatherapp.core.facade.station.media;

import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.enumeration.error.ErrorCodeEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationMediaFacadeImpl implements StationMediaFacade {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaFileData> stationMediaFileMapper;

    @Override
    public StationMediaFileData getStationMediaFile(String name, String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            throw new CoreException(ErrorCodeEnum.CODE00020, stationCode);
        }
        StationMediaEntity stationMedia = stationMediaRepository.findByNameAndStation(name, station);
        if (stationMedia == null) {
            throw new CoreException(ErrorCodeEnum.CODE00021, name, station.getCode());
        }
        return stationMediaFileMapper.map(stationMedia);
    }
}
