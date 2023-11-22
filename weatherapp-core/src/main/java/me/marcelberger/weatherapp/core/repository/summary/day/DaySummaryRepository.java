package me.marcelberger.weatherapp.core.repository.summary.day;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DaySummaryRepository<ENTITY> {
    Page<ENTITY> findAllDaysInRangeForStation(Pageable pageable, StationEntity station, String startDay, String endDay);

    Page<ENTITY> findAllDaysFromStartDayForStation(Pageable pageable, StationEntity station, String startDay);

    Page<ENTITY> findAllDaysToEndDayForStation(Pageable pageable, StationEntity station, String endDay);

    Page<ENTITY> findAllDaysForStation(Pageable pageable, StationEntity station);

    Page<ENTITY> findAllDaysOfMonthForStation(Pageable pageable, StationEntity station, String yearMonth);

    ENTITY findByStationAndDay(StationEntity station, String day);

    ENTITY save(ENTITY s);
}
