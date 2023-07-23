package me.marcelberger.weatherapp.core.repository.data.single.impl;

import me.marcelberger.weatherapp.core.entity.data.single.WeatherSingleDataEntity;
import me.marcelberger.weatherapp.core.repository.data.DataRepositoryQueries;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherSingleDataRepositoryImpl extends SingleDataRepository<WeatherSingleDataEntity>, JpaRepository<WeatherSingleDataEntity, Long> {

    @Override
    @Query(value = DataRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.station_id = :station_id " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherSingleDataEntity findLatestByStationId(@Param("station_id") Long stationId);

    @Override
    @Query(value = DataRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.timestamp >= :timestamp_start " +
            "AND w.timestamp <= :timestamp_end " +
            "AND w.station_id = :station_id " +
            "ORDER BY w.timestamp ASC ",
            countQuery = DataRepositoryQueries.SELECT_FROM_WEATHER +
                    "WHERE w.timestamp >= :timestamp_start " +
                    "AND w.timestamp <= :timestamp_end " +
                    "AND w.station_id = :station_id " +
                    "ORDER BY w.timestamp ASC ",
            nativeQuery = true
    )
    Page<WeatherSingleDataEntity> findAllInTimestampRangeByStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);

    @Query(value = DataRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.station_id = :station_id " +
            "AND w.rain_delta != 0 " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherSingleDataEntity findLatestWithRain(@Param("station_id") Long stationId);
}
