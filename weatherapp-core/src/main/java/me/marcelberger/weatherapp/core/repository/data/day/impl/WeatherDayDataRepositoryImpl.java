package me.marcelberger.weatherapp.core.repository.data.day.impl;

import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.data.day.DayDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDayDataRepositoryImpl extends DayDataRepository<WeatherDayDataEntity>, JpaRepository<WeatherDayDataEntity, Long> {

    @Override
    WeatherDayDataEntity findByStationAndDay(StationEntity station, String day);

    @Override
    @Query(value = "SELECT w FROM WeatherDayDataEntity w WHERE w.station = :station")
    Page<WeatherDayDataEntity> findAllDaysForStation(Pageable pageable, @Param("station") StationEntity station);

    @Override
    @Query(value = "SELECT w FROM WeatherDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay " +
            "AND w.day <= :endDay")
    Page<WeatherDayDataEntity> findAllDaysInRangeForStation(Pageable pageable,
                                                            @Param("station") StationEntity station,
                                                            @Param("startDay") String startDay,
                                                            @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day <= :endDay")
    Page<WeatherDayDataEntity> findAllDaysToEndDayForStation(Pageable pageable,
                                                             @Param("station") StationEntity station,
                                                             @Param("endDay") String endDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay")
    Page<WeatherDayDataEntity> findAllDaysFromStartDayForStation(Pageable pageable,
                                                                 @Param("station") StationEntity station,
                                                                 @Param("startDay") String startDay);

    @Override
    @Query(value = "SELECT w FROM WeatherDayDataEntity w " +
            "WHERE w.station = :station " +
            "AND w.day LIKE :yearMonth")
    Page<WeatherDayDataEntity> findAllDaysOfMonthForStation(Pageable pageable,
                                                            @Param("station") StationEntity station,
                                                            @Param("yearMonth") String yearMonth);
}
