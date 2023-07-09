package me.marcelberger.weatherapp.api.mapper.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.WeatherSingleDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.data.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherDataMapperImpl implements DataMapper<WeatherDataEntity, WeatherSingleDataResponseDto> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private MessageService msg;

    @Override
    public WeatherSingleDataResponseDto map(WeatherDataEntity object) {
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

    private Long getSecondsSinceLast(WeatherDataEntity entity) {
        WeatherDataEntity last = weatherRepository.findFirstBeforeTimestampByStationId(
                entity.getStation().getId(),
                entity.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (last != null) {
            return entity.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                    - last.getTimestamp().toEpochSecond(ZoneOffset.UTC);
        }
        return null;
    }
}
