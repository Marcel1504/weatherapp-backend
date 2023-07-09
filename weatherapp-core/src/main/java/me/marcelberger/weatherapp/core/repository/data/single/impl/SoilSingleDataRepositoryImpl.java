package me.marcelberger.weatherapp.core.repository.data.single.impl;

import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import me.marcelberger.weatherapp.core.repository.data.single.SingleDataRepository;
import me.marcelberger.weatherapp.core.repository.soil.SoilRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilSingleDataRepositoryImpl extends SingleDataRepository<SoilDataEntity>, JpaRepository<SoilDataEntity, Long> {

    @Override
    @Query(value = SoilRepositoryQueries.SELECT_FROM +
            "WHERE s.station_id = :station_id " +
            "ORDER BY s.timestamp DESC " +
            "LIMIT 1",
            nativeQuery = true
    )
    SoilDataEntity findLatestByStationId(@Param("station_id") Long stationId);
}
