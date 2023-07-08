package me.marcelberger.weatherapp.core.repository.soil;

import me.marcelberger.weatherapp.core.entity.soil.SoilEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilRepository extends JpaRepository<SoilEntity, Long> {

    @Query(value = SoilRepositoryQueries.SELECT_FROM +
            "WHERE s.station_id = :station_id " +
            "ORDER BY s.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    SoilEntity findLatestByStationId(@Param("station_id") Long stationId);

    @Query(value = SoilRepositoryQueries.SELECT_FROM +
            "WHERE s.timestamp >= :timestamp_start " +
            "AND s.timestamp <= :timestamp_end " +
            "AND s.station_id = :station_id " +
            "ORDER BY s.timestamp ASC ",
            countQuery = SoilRepositoryQueries.SELECT_FROM +
                    "WHERE s.timestamp >= :timestamp_start " +
                    "AND s.timestamp <= :timestamp_end " +
                    "AND s.station_id = :station_id " +
                    "ORDER BY s.timestamp ASC ",
            nativeQuery = true
    )
    Page<SoilEntity> findAllInTimestampRangeForStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);
}
