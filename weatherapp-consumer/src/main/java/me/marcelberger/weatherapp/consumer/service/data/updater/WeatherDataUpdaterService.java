package me.marcelberger.weatherapp.consumer.service.data.updater;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class WeatherDataUpdaterService<FROM> extends DataUpdaterService<FROM, WeatherSingleSummaryEntity> {

    @Autowired
    private SingleSummaryRepository<WeatherSingleSummaryEntity> weatherRepository;

    @Override
    protected WeatherSingleSummaryEntity getLatestExistingEntityForStation(StationEntity station) {
        return weatherRepository.findLatestByStationId(station.getId());
    }

    @Override
    protected LocalDateTime getTimestampOfEntity(WeatherSingleSummaryEntity entity) {
        return entity != null ? entity.getTimestamp() : null;
    }

    @Override
    protected WeatherSingleSummaryEntity createNewEntity(FROM data, StationEntity station) {
        return WeatherSingleSummaryEntity.builder()
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
    protected void saveEntity(WeatherSingleSummaryEntity entity) {
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
