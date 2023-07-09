package me.marcelberger.weatherapp.core.repository.data.single.impl;

import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import me.marcelberger.weatherapp.core.repository.weather.WeatherRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherSingleDataRepositoryImpl extends SingleDataRepository<WeatherDataEntity>, JpaRepository<WeatherDataEntity, Long> {

    @Override
    @Query(value = WeatherRepositoryQueries.SELECT_FROM +
            "WHERE w.station_id = :station_id " +
            "ORDER BY w.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    WeatherDataEntity findLatestByStationId(@Param("station_id") Long stationId);
}
