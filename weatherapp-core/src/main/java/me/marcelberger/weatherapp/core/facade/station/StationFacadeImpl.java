package me.marcelberger.weatherapp.core.facade.station;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.service.station.StationService;
import me.marcelberger.weatherapp.core.service.station.media.StationMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationFacadeImpl implements StationFacade {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationMediaService stationMediaService;

    @Autowired
    private Mapper<StationEntity, StationData> stationMapper;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaFileData> stationMediaFileMapper;

    @Override
    public PageData<StationData> getAllStations() {
        return stationMapper.mapToPage(stationService.getAll());
    }

    @Override
    public StationData getStationByCode(String stationCode) {
        return stationMapper.map(stationService.getByStationCode(stationCode));
    }

    @Override
    public StationMediaFileData getStationMediaFileByNameAndStationCode(String name, String stationCode) {
        StationEntity station = stationService.getByStationCode(stationCode);
        StationMediaEntity stationMedia = stationMediaService.getStationMediaByNameAndStation(name, station);
        return stationMediaFileMapper.map(stationMedia);
    }
}
