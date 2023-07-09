package me.marcelberger.weatherapp.aggregator.service.aggregation.weather;

import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public abstract class WeatherAggregationService<TARGET> extends AggregationService<WeatherDataEntity, TARGET> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    protected Page<WeatherDataEntity> loadSourceEntities(PageRequest request,
                                                         StationEntity station,
                                                         String startTimestamp,
                                                         String endTimestamp) {
        return weatherRepository.findAllInTimestampRangeByStationId(
                request,
                station.getId(),
                startTimestamp,
                endTimestamp);
    }
}
