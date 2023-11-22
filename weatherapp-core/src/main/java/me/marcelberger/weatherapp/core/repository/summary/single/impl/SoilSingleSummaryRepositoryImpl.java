package me.marcelberger.weatherapp.core.repository.summary.single.impl;

import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.SummaryRepositoryQueries;
import me.marcelberger.weatherapp.core.repository.summary.single.SingleSummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilSingleSummaryRepositoryImpl extends SingleSummaryRepository<SoilSingleSummaryEntity>, JpaRepository<SoilSingleSummaryEntity, Long> {

    @Override
    @Query(value = SummaryRepositoryQueries.SELECT_FROM_SOIL +
            "WHERE s.station_id = :station_id " +
            "ORDER BY s.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    SoilSingleSummaryEntity findLatestByStationId(@Param("station_id") Long stationId);

    @Override
    @Query(value = SummaryRepositoryQueries.SELECT_FROM_SOIL +
            "WHERE s.timestamp >= :timestamp_start " +
            "AND s.timestamp <= :timestamp_end " +
            "AND s.station_id = :station_id " +
            "ORDER BY s.timestamp ASC ",
            countQuery = SummaryRepositoryQueries.SELECT_FROM_SOIL +
                    "WHERE s.timestamp >= :timestamp_start " +
                    "AND s.timestamp <= :timestamp_end " +
                    "AND s.station_id = :station_id " +
                    "ORDER BY s.timestamp ASC ",
            nativeQuery = true
    )
    Page<SoilSingleSummaryEntity> findAllInTimestampRangeByStationId(
            Pageable pageable,
            @Param("station_id") Long stationId,
            @Param("timestamp_start") String timestampStart,
            @Param("timestamp_end") String timestampEnd);
}
