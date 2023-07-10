package me.marcelberger.weatherapp.api.mapper.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.WeatherSingleDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherDataMapperImpl implements Mapper<WeatherSingleDataEntity, WeatherSingleDataResponseDto> {

    @Autowired
    private SingleDataRepository<WeatherSingleDataEntity> weatherSingleDataRepository;

    @Override
    public WeatherSingleDataResponseDto map(WeatherSingleDataEntity object) {
        return WeatherSingleDataResponseDto.builder()
                .temperature(object.getTemperature())
                .humidity(object.getHumidity())
                .rainRate(object.getRainRate())
                .rainSinceLast(object.getRainDelta())
                .pressure(object.getPressure())
                .wind(object.getWind())
                .windDirection(object.getWindDirection())
                .solarRadiation(object.getSolarRadiation())
                .timestamp(object.getTimestamp())
                .secondsSinceLast(getSecondsSinceLast(object))
                .build();
    }

    private Long getSecondsSinceLast(WeatherSingleDataEntity entity) {
        WeatherSingleDataEntity last = weatherSingleDataRepository.findFirstBeforeTimestampByStationId(
                entity.getStation().getId(),
                entity.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (last != null) {
            return entity.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                    - last.getTimestamp().toEpochSecond(ZoneOffset.UTC);
        }
        return null;
    }
}
