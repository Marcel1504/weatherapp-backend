package me.marcelberger.weatherapp.core.repository;

import me.marcelberger.weatherapp.core.entity.StationEntity;
import me.marcelberger.weatherapp.core.entity.StationParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationParameterRepository extends JpaRepository<StationParameterEntity, Long> {
    StationParameterEntity findByStationAndCode(StationEntity station, String code);
}
