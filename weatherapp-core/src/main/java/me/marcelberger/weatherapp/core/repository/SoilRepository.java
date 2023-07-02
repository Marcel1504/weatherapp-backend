package me.marcelberger.weatherapp.core.repository;

import me.marcelberger.weatherapp.core.entity.SoilEntity;
import me.marcelberger.weatherapp.core.entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SoilRepository extends JpaRepository<SoilEntity, Long> {

    SoilEntity findByTimestampAndStation(LocalDateTime timestamp, StationEntity station);

    @Query(value = SoilRepositoryQueries.SELECT_FROM +
            "WHERE s.station_id = :station_id " +
            "ORDER BY s.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    SoilEntity findLatestByStationId(@Param("station_id") Long stationId);
}
