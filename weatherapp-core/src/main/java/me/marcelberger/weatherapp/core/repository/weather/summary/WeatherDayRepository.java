package me.marcelberger.weatherapp.core.repository.weather.summary;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherDayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDayRepository extends JpaRepository<WeatherDayEntity, Long> {
    WeatherDayEntity findByStationAndDay(StationEntity station, String day);

    @Query(value = "SELECT w FROM WeatherDayEntity w WHERE w.station = :station")
    Page<WeatherDayEntity> findAllDaysForStation(Pageable pageable, @Param("station") StationEntity station);

    @Query(value = "SELECT w FROM WeatherDayEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay " +
            "AND w.day <= :endDay")
    Page<WeatherDayEntity> findAllDaysInRangeForStation(Pageable pageable,
                                                        @Param("station") StationEntity station,
                                                        @Param("startDay") String startDay,
                                                        @Param("endDay") String endDay);

    @Query(value = "SELECT w FROM WeatherDayEntity w " +
            "WHERE w.station = :station " +
            "AND w.day <= :endDay")
    Page<WeatherDayEntity> findAllDaysToEndForStation(Pageable pageable,
                                                      @Param("station") StationEntity station,
                                                      @Param("endDay") String endDay);

    @Query(value = "SELECT w FROM WeatherDayEntity w " +
            "WHERE w.station = :station " +
            "AND w.day >= :startDay")
    Page<WeatherDayEntity> findAllDaysFromStartForStation(Pageable pageable,
                                                          @Param("station") StationEntity station,
                                                          @Param("startDay") String startDay);

    @Query(value = "SELECT w FROM WeatherDayEntity w " +
            "WHERE w.station = :station " +
            "AND w.day LIKE :yearMonth")
    Page<WeatherDayEntity> findAllDaysOfMonthForStation(Pageable pageable,
                                                        @Param("station") StationEntity station,
                                                        @Param("yearMonth") String yearMonth);
}
