package me.marcelberger.weatherapp.core.repository.summary.day.impl;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDaySummaryRepositoryImpl extends DaySummaryRepository<WeatherDaySummaryEntity>, JpaRepository<WeatherDaySummaryEntity, Long> {

    @Override
    WeatherDaySummaryEntity findByStationAndDay(StationEntity station, String day);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w WHERE w.station = :station")
    Page<WeatherDaySummaryEntity> findAllDaysForStation(Pageable pageable, @Param("station") StationEntity station);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w")
    Page<WeatherDaySummaryEntity> findAllDays(Pageable pageable);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay " +
            "AND w.day <= :endDay")
    Page<WeatherDaySummaryEntity> findAllDaysInRangeForStation(Pageable pageable,
                                                               @Param("station") StationEntity station,
                                                               @Param("startDay") String startDay,
                                                               @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day <= :endDay")
    Page<WeatherDaySummaryEntity> findAllDaysToEndDayForStation(Pageable pageable,
                                                                @Param("station") StationEntity station,
                                                                @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay")
    Page<WeatherDaySummaryEntity> findAllDaysFromStartDayForStation(Pageable pageable,
                                                                    @Param("station") StationEntity station,
                                                                    @Param("startDay") String startDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDaySummaryEntity w " +
            "WHERE w.station = :station " +
            "AND w.day LIKE :yearMonth")
    Page<WeatherDaySummaryEntity> findAllDaysOfMonthForStation(Pageable pageable,
                                                               @Param("station") StationEntity station,
                                                               @Param("yearMonth") String yearMonth);
}
