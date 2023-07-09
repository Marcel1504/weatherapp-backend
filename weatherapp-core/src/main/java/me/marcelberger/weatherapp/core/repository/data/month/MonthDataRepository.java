package me.marcelberger.weatherapp.core.repository.data.month;

import me.marcelberger.weatherapp.core.entity.data.DataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonthDataRepository<ENTITY extends DataEntity> {
    ENTITY findByStationAndMonthAndYear(StationEntity station, String month, String year);

    Page<ENTITY> findAllByStationAndYear(Pageable pageable, StationEntity station, String year);

    Page<ENTITY> findAllByStation(Pageable pageable, StationEntity station);
}
