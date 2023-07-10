package me.marcelberger.weatherapp.core.repository.data.year;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface YearDataRepository<ENTITY> {
    ENTITY findByStationAndYear(StationEntity station, String year);

    Page<ENTITY> findAllByStation(Pageable pageable, StationEntity station);

    ENTITY save(ENTITY s);
}
