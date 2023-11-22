package me.marcelberger.weatherapp.core.mapper.station;

import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.data.station.StationMediaData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StationDataMapperImpl implements Mapper<StationEntity, StationData> {

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaData> stationMediaMapper;

    @Value("${weatherapp.station.media.latestKey}")
    private String stationMediaLatestKey;

    @Override
    public StationData map(StationEntity object) {
        return StationData.builder()
                .code(object.getCode())
                .name(object.getName())
                .latitude(object.getLatitude())
                .longitude(object.getLongitude())
                .altitude(object.getAltitude())
                .latestStationMedia(getLatestStationMedia(object))
                .lastActivity(object.getLastActivity())
                .type(object.getType())
                .build();
    }

    private StationMediaData getLatestStationMedia(StationEntity station) {
        StationMediaEntity media = stationMediaRepository.findByNameAndStation(stationMediaLatestKey, station);
        if (media != null) {
            return stationMediaMapper.map(media);
        }
        return null;
    }
}
