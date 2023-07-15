package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.dto.station.StationData;
import me.marcelberger.weatherapp.api.dto.station.StationMediaData;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationMapperImpl implements Mapper<StationEntity, StationData> {

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private Mapper<StationMediaEntity, StationMediaData> stationMediaDataMapper;

    @Value("${weatherapp.station.media.baseUrl}")
    private String stationMediaBaseUrl;

    @Override
    public StationData map(StationEntity object) {
        return StationData.builder()
                .code(object.getCode())
                .name(object.getName())
                .latitude(object.getLatitude())
                .longitude(object.getLongitude())
                .altitude(object.getAltitude())
                .stationImageUrls(getStationImageUrls(object))
                .lastActivity(object.getLastActivity())
                .type(object.getType())
                .build();
    }

    private List<StationMediaData> getStationImageUrls(StationEntity station) {
        return stationMediaRepository.findAllByStation(station).stream()
                .filter(media -> media.getName() != null)
                .map(media -> stationMediaDataMapper.map(media))
                .toList();
    }
}
