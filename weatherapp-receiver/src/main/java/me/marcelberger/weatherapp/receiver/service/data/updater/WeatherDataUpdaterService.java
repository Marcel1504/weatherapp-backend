package me.marcelberger.weatherapp.receiver.service.data.updater;

import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class WeatherDataUpdaterService<FROM> extends DataUpdaterService<FROM, WeatherDataEntity> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    protected WeatherDataEntity getLatestExistingEntityForStation(StationEntity station) {
        return weatherRepository.findLatestForStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(WeatherDataEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected WeatherDataEntity createNewEntity(FROM data, StationEntity station) {
        return WeatherDataEntity.builder()
                .station(station)
                .timestamp(getTimestamp(data))
                .temperature(getTemperature(data))
                .rainDelta(getRainDelta(data, station))
                .rainRate(getRainRate(data))
                .humidity(getHumidity(data))
                .pressure(getPressure(data))
                .solarRadiation(getSolarRadiation(data))
                .wind(getWind(data))
                .windDirection(getWindDirection(data))
                .build();
    }

    @Override
    protected void saveEntity(WeatherDataEntity entity) {
        weatherRepository.save(entity);
    }

    protected abstract LocalDateTime getTimestamp(FROM data);

    protected abstract Double getTemperature(FROM data);

    protected abstract Integer getHumidity(FROM data);

    protected abstract Double getRainDelta(FROM data, StationEntity station);

    protected abstract Double getRainRate(FROM data);

    protected abstract Double getWind(FROM data);

    protected abstract Integer getWindDirection(FROM data);

    protected abstract Double getPressure(FROM data);

    protected abstract Double getSolarRadiation(FROM data);
}
