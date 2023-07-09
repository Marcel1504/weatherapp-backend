package me.marcelberger.weatherapp.aggregator.service.synchronization.impl;

import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.enumeration.StationTypeEnum;
import me.marcelberger.weatherapp.core.repository.soil.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SoilSynchronizationServiceImpl extends SynchronizationService<SoilDataEntity> {

    @Autowired
    private SoilRepository soilRepository;

    @Override
    public StationTypeEnum getStationType() {
        return StationTypeEnum.SOIL;
    }

    @Override
    protected LocalDateTime getLatestSourceTimestamp(StationEntity station) {
        return soilRepository.findLatestByStationId(station.getId()).getTimestamp();
    }
}
