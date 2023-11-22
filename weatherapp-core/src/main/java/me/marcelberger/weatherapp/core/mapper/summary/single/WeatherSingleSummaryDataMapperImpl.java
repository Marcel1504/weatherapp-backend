package me.marcelberger.weatherapp.core.mapper.summary.single;

import me.marcelberger.weatherapp.core.data.summary.single.WeatherSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import me.marcelberger.weatherapp.core.repository.summary.single.impl.WeatherSingleSummaryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherSingleSummaryDataMapperImpl implements Mapper<
        WeatherSingleSummaryEntity,
        WeatherSingleSummaryData> {

    @Autowired
    private WeatherSingleSummaryRepositoryImpl weatherSingleSummaryDataRepository;

    @Override
    public WeatherSingleSummaryData map(WeatherSingleSummaryEntity object) {
        return WeatherSingleSummaryData.builder()
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

    private LocalDateTime getLastRain(WeatherSingleSummaryEntity entity) {
        WeatherSingleSummaryEntity last =
                weatherSingleSummaryDataRepository.findLatestWithRain(entity.getStation().getId());
        if (last != null && last.getTimestamp().isBefore(entity.getTimestamp())) {
            return last.getTimestamp();
        }
        return null;
    }
}
