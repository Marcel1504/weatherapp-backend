package me.marcelberger.weatherapp.core.repository.data.single.impl;

import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import me.marcelberger.weatherapp.core.repository.data.DataRepositoryQueries;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilSingleDataRepositoryImpl extends SingleDataRepository<SoilSingleDataEntity>, JpaRepository<SoilSingleDataEntity, Long> {

    @Override
    @Query(value = DataRepositoryQueries.SELECT_FROM_SOIL +
            "WHERE s.station_id = :station_id " +
            "ORDER BY s.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    SoilSingleDataEntity findLatestByStationId(@Param("station_id") Long stationId);

    @Override
    @Query(value = DataRepositoryQueries.SELECT_FROM_SOIL +
            "WHERE s.timestamp >= :timestamp_start " +
            "AND s.timestamp <= :timestamp_end " +
            "AND s.station_id = :station_id " +
            "ORDER BY s.timestamp ASC ",
            countQuery = DataRepositoryQueries.SELECT_FROM_SOIL +
                    "WHERE s.timestamp >= :timestamp_start " +
                    "AND s.timestamp <= :timestamp_end " +
                    "AND s.station_id = :station_id " +
                    "ORDER BY s.timestamp ASC ",
            nativeQuery = true
    )
    Page<SoilSingleDataEntity> findAllInTimestampRangeByStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);
}
