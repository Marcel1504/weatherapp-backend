package me.marcelberger.weatherapp.receiver.service.data.updater;

import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class WeatherDataUpdaterService<FROM> extends DataUpdaterService<FROM, WeatherSingleDataEntity> {

    @Autowired
    private SingleDataRepository<WeatherSingleDataEntity> weatherRepository;

    @Override
    protected WeatherSingleDataEntity getLatestExistingEntityForStation(StationEntity station) {
        return weatherRepository.findLatestByStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(WeatherSingleDataEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected WeatherSingleDataEntity createNewEntity(FROM data, StationEntity station) {
        return WeatherSingleDataEntity.builder()
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
    protected void saveEntity(WeatherSingleDataEntity entity) {
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
