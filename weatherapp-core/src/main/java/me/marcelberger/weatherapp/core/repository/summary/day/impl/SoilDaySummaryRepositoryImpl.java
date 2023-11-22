package me.marcelberger.weatherapp.core.repository.summary.day.impl;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilDaySummaryRepositoryImpl extends DaySummaryRepository<SoilDaySummaryEntity>, JpaRepository<SoilDaySummaryEntity, Long> {

    @Override
    SoilDaySummaryEntity findByStationAndDay(StationEntity station, String day);

    @Override
    @Query(value = "SELECT w FROM SoilDaySummaryEntity w WHERE w.station = :station")
    Page<SoilDaySummaryEntity> findAllDaysForStation(Pageable pageable, @Param("station") StationEntity station);

    @Override
    @Query(value = "SELECT w FROM SoilDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay " +
            "AND w.day <= :endDay")
    Page<SoilDaySummaryEntity> findAllDaysInRangeForStation(Pageable pageable,
                                                            @Param("station") StationEntity station,
                                                            @Param("startDay") String startDay,
                                                            @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM SoilDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day <= :endDay")
    Page<SoilDaySummaryEntity> findAllDaysToEndDayForStation(Pageable pageable,
                                                             @Param("station") StationEntity station,
                                                             @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM SoilDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay")
    Page<SoilDaySummaryEntity> findAllDaysFromStartDayForStation(Pageable pageable,
                                                                 @Param("station") StationEntity station,
                                                                 @Param("startDay") String startDay);

    @Override
    @Query(value = "SELECT w FROM SoilDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day LIKE :yearMonth")
    Page<SoilDaySummaryEntity> findAllDaysOfMonthForStation(Pageable pageable,
                                                            @Param("station") StationEntity station,
                                                            @Param("yearMonth") String yearMonth);
}
