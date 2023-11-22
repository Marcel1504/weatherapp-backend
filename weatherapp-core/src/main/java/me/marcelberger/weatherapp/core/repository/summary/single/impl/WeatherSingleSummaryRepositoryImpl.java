package me.marcelberger.weatherapp.core.repository.summary.single.impl;

import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.SummaryRepositoryQueries;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherSingleSummaryRepositoryImpl extends SingleSummaryRepository<WeatherSingleSummaryEntity>, JpaRepository<WeatherSingleSummaryEntity, Long> {

    @Override
    @Query(value = SummaryRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.station_id = :station_id " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherSingleSummaryEntity findLatestByStationId(@Param("station_id") Long stationId);

    @Override
    @Query(value = SummaryRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.timestamp >= :timestamp_start " +
            "AND w.timestamp <= :timestamp_end " +
            "AND w.station_id = :station_id " +
            "ORDER BY w.timestamp ASC ",
            countQuery = SummaryRepositoryQueries.SELECT_FROM_WEATHER +
                    "WHERE w.timestamp >= :timestamp_start " +
                    "AND w.timestamp <= :timestamp_end " +
                    "AND w.station_id = :station_id " +
                    "ORDER BY w.timestamp ASC ",
            nativeQuery = true
    )
    Page<WeatherSingleSummaryEntity> findAllInTimestampRangeByStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);

    @Query(value = SummaryRepositoryQueries.SELECT_FROM_WEATHER +
            "WHERE w.station_id = :station_id " +
            "AND w.rain_delta != 0 " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherSingleSummaryEntity findLatestWithRain(@Param("station_id") Long stationId);
}
