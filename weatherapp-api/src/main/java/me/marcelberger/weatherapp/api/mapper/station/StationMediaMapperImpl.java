package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.dto.response.station.StationMediaResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StationMediaMapperImpl implements Mapper<StationMediaEntity, StationMediaResponseDto> {

    @Value("${weatherapp.station.media.baseUrl}")
    private String stationMediaBaseUrl;

    @Override
    public StationMediaResponseDto map(StationMediaEntity object) {
        return StationMediaResponseDto.builder()
                .created(object.getCreationTimestamp())
                .url(stationMediaBaseUrl
                        .replace("{station}", object.getStation().getCode())
                        .replace("{name}", object.getName()))
                .build();
    }
}
