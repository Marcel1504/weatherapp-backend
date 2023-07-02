package me.marcelberger.weatherapp.core.repository.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationParameterRepository extends JpaRepository<StationParameterEntity, Long> {
    StationParameterEntity findByStationAndCode(StationEntity station, String code);
}
