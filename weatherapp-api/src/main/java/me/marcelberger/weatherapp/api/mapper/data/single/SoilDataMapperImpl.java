package me.marcelberger.weatherapp.api.mapper.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.SoilSingleDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class SoilDataMapperImpl implements Mapper<SoilSingleDataEntity, SoilSingleDataResponseDto> {

    @Autowired
    private SingleDataRepository<SoilSingleDataEntity> soilSingleDataRepository;

    @Override
    public SoilSingleDataResponseDto map(SoilSingleDataEntity object) {
        return SoilSingleDataResponseDto.builder()
                .temperature50cm(object.getTemperature50cm())
                .temperature100cm(object.getTemperature100cm())
                .temperature200cm(object.getTemperature200cm())
                .secondsSinceLast(getSecondsSinceLast(object))
                .timestamp(object.getTimestamp())
                .build();
    }

    private Long getSecondsSinceLast(SoilSingleDataEntity entity) {
        SoilSingleDataEntity last = soilSingleDataRepository.findFirstBeforeTimestampByStationId(
                entity.getStation().getId(),
                entity.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (last != null) {
            return entity.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                    - last.getTimestamp().toEpochSecond(ZoneOffset.UTC);
        }
        return null;
    }
}
