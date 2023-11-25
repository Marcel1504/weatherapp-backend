package me.marcelberger.weatherapp.core.service.station.media;

import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationMediaServiceImpl implements StationMediaService {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaFileData> stationMediaFileMapper;

    @Override
    public StationMediaEntity getStationMediaByNameAndStation(String name, StationEntity station) {
        if (name == null || name.isBlank()) {
            throw new CoreError(CoreError.Code.CORE00300, "Name for StationMedia not provided");
        }
        if (station == null || station.getCode() == null) {
            throw new CoreError(CoreError.Code.CORE00300, "Station for StationMedia not provided");
        }
        StationMediaEntity stationMedia = stationMediaRepository.findByNameAndStation(name, station);
        if (stationMedia == null) {
            throw new CoreError(
                    CoreError.Code.CORE00300,
                    "StationMedia(name=%s,stationCode=%s) not found", name, station.getCode());
        }
        return stationMedia;
    }
}
