package me.marcelberger.weatherapp.aggregator.service.aggregation.soil;

import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.soil.SoilEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.soil.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class SoilAggregationService<TARGET> extends AggregationService<SoilEntity, TARGET> {

    @Autowired
    private SoilRepository soilRepository;

    @Override
    protected Page<SoilEntity> loadSourceEntities(PageRequest request,
                                                  StationEntity station,
                                                  String startTimestamp,
                                                  String endTimestamp) {
        return soilRepository.findAllInTimestampRangeForStationId(
                request,
                station.getId(),
                startTimestamp,
                endTimestamp);
    }
}
