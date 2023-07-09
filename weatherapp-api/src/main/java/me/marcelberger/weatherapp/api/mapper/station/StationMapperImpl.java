package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.dto.station.StationData;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.stereotype.Component;

@Component
public class StationMapperImpl implements Mapper<StationEntity, StationData> {

    @Override
    public StationData map(StationEntity object) {
        return StationData.builder()
                .code(object.getCode())
                .name(object.getName())
                .latitude(object.getLatitude())
                .longitude(object.getLongitude())
                .altitude(object.getAltitude())
                .type(object.getType())
                .build();
    }
}
