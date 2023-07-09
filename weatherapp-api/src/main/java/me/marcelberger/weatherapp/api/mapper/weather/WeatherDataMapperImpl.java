package me.marcelberger.weatherapp.api.mapper.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherDataMapperImpl implements DataMapper<WeatherEntity, WeatherData> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private MessageService msg;

    @Override
    public WeatherData map(WeatherEntity object) {
        return WeatherData.builder()
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

    private Long getSecondsSinceLast(WeatherEntity entity) {
        WeatherEntity last = weatherRepository.findFirstBeforeTimestampForStationId(
                entity.getStation().getId(),
                entity.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (last != null) {
            return entity.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                    - last.getTimestamp().toEpochSecond(ZoneOffset.UTC);
        }
        return null;
    }
}
