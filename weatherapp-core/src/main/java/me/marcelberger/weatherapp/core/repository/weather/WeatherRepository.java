package me.marcelberger.weatherapp.core.repository.weather;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    WeatherEntity findByTimestampAndStation(LocalDateTime timestamp, StationEntity station);

    @Query(value = WeatherRepositoryQueries.SELECT_FROM +
            "WHERE w.station_id = :station_id " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherEntity findLatestForStationId(@Param("station_id") Long stationId);
}

