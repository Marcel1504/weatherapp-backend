package me.marcelberger.weatherapp.core.repository.data.day.impl;

import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.day.DayDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilDayDataRepositoryImpl extends DayDataRepository<SoilDayDataEntity>, JpaRepository<SoilDayDataEntity, Long> {

    @Override
    SoilDayDataEntity findByStationAndDay(StationEntity station, String day);

    @Override
    @Query(value = "SELECT w FROM SoilDayDataEntity w WHERE w.station = :station")
    Page<SoilDayDataEntity> findAllDaysForStation(Pageable pageable, @Param("station") StationEntity station);

    @Override
    @Query(value = "SELECT w FROM SoilDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay " +
            "AND w.day <= :endDay")
    Page<SoilDayDataEntity> findAllDaysInRangeForStation(Pageable pageable,
                                                         @Param("station") StationEntity station,
                                                         @Param("startDay") String startDay,
                                                         @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM SoilDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day <= :endDay")
    Page<SoilDayDataEntity> findAllDaysToEndDayForStation(Pageable pageable,
                                                          @Param("station") StationEntity station,
                                                          @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM SoilDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay")
    Page<SoilDayDataEntity> findAllDaysFromStartDayForStation(Pageable pageable,
                                                              @Param("station") StationEntity station,
                                                              @Param("startDay") String startDay);

    @Override
    @Query(value = "SELECT w FROM SoilDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day LIKE :yearMonth")
    Page<SoilDayDataEntity> findAllDaysOfMonthForStation(Pageable pageable,
                                                         @Param("station") StationEntity station,
                                                         @Param("yearMonth") String yearMonth);
}
