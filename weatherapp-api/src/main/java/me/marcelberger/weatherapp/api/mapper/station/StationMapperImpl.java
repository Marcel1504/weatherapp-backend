package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.dto.response.station.StationMediaResponseDto;
import me.marcelberger.weatherapp.api.dto.response.station.StationResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StationMapperImpl implements Mapper<StationEntity, StationResponseDto> {

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaResponseDto> stationMediaMapper;

    @Value("${weatherapp.station.media.latestKey}")
    private String stationMediaLatestKey;

    @Override
    public StationResponseDto map(StationEntity object) {
        return StationResponseDto.builder()
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

    private StationMediaResponseDto getLatestStationMedia(StationEntity station) {
        StationMediaEntity media = stationMediaRepository.findByNameAndStation(stationMediaLatestKey, station);
        if (media != null) {
            return stationMediaMapper.map(media);
        }
        return null;
    }
}
