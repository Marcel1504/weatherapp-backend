package me.marcelberger.weatherapp.core.repository.data.hour;

import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HourDataRepository<ENTITY extends DataEntity> {
    Page<ENTITY> findAllByStationAndDay(Pageable pageable, StationEntity station, String day);
}
