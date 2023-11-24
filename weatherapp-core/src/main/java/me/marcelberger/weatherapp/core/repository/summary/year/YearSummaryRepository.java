package me.marcelberger.weatherapp.core.repository.summary.year;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface YearSummaryRepository<ENTITY> {
    ENTITY findByStationAndYear(StationEntity station, String year);

    Page<ENTITY> findAllByStation(Pageable pageable, StationEntity station);

    Page<ENTITY> findAll(Pageable pageable);

    ENTITY save(ENTITY s);
}
