package me.marcelberger.weatherapp.aggregator.service.aggregation.soil;

import me.marcelberger.weatherapp.aggregator.service.aggregation.AggregationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.soil.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class SoilAggregationService<TARGET> extends AggregationService<SoilDataEntity, TARGET> {

    @Autowired
    private SoilRepository soilRepository;

    @Override
    protected Page<SoilDataEntity> loadSourceEntities(PageRequest request,
                                                      StationEntity station,
                                                      String startTimestamp,
                                                      String endTimestamp) {
        return soilRepository.findAllInTimestampRangeByStationId(
                request,
                station.getId(),
                startTimestamp,
                endTimestamp);
    }
}
