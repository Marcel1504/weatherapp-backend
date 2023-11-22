package me.marcelberger.weatherapp.core.repository.summary.month;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonthSummaryRepository<ENTITY> {
    ENTITY findByStationAndMonthAndYear(StationEntity station, String month, String year);

    Page<ENTITY> findAllByStationAndYear(Pageable pageable, StationEntity station, String year);

    Page<ENTITY> findAllByStation(Pageable pageable, StationEntity station);

    ENTITY save(ENTITY s);
}
