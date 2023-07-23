package me.marcelberger.weatherapp.api.mapper.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.WeatherSingleDataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.repository.data.single.impl.WeatherSingleDataRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherDataMapperImpl implements Mapper<WeatherSingleDataEntity, WeatherSingleDataResponseDto> {

    @Autowired
    private WeatherSingleDataRepositoryImpl weatherSingleDataRepository;

    @Override
    public WeatherSingleDataResponseDto map(WeatherSingleDataEntity object) {
        return WeatherSingleDataResponseDto.builder()
                .temperature(object.getTemperature())
                .humidity(object.getHumidity())
                .rainRate(object.getRainRate())
                .pressure(object.getPressure())
                .wind(object.getWind())
                .windDirection(object.getWindDirection())
                .solarRadiation(object.getSolarRadiation())
                .lastRain(getLastRain(object))
                .timestamp(object.getTimestamp())
                .build();
    }

    private LocalDateTime getLastRain(WeatherSingleDataEntity entity) {
        WeatherSingleDataEntity last = weatherSingleDataRepository.findLatestWithRain(entity.getStation().getId());
        if (last != null && last.getTimestamp().isBefore(entity.getTimestamp())) {
            return last.getTimestamp();
        }
        return null;
    }
}
