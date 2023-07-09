package me.marcelberger.weatherapp.core.repository.weather;

import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    @Query(value = WeatherRepositoryQueries.SELECT_FROM +
            "WHERE w.station_id = :station_id " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherEntity findLatestForStationId(@Param("station_id") Long stationId);

    @Query(value = WeatherRepositoryQueries.SELECT_FROM +
            "WHERE w.station_id = :station_id " +
            "AND w.timestamp < :timestamp " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherEntity findFirstBeforeTimestampForStationId(
            @Param("station_id") Long stationId,
            @Param("timestamp") String timestamp);

    @Query(value = WeatherRepositoryQueries.SELECT_FROM +
            "WHERE w.timestamp >= :timestamp_start " +
            "AND w.timestamp <= :timestamp_end " +
            "AND w.station_id = :station_id " +
            "ORDER BY w.timestamp ASC ",
            countQuery = WeatherRepositoryQueries.SELECT_FROM +
                    "WHERE w.timestamp >= :timestamp_start " +
                    "AND w.timestamp <= :timestamp_end " +
                    "AND w.station_id = :station_id " +
                    "ORDER BY w.timestamp ASC ",
            nativeQuery = true
    )
    Page<WeatherEntity> findAllInTimestampRangeForStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);
}

