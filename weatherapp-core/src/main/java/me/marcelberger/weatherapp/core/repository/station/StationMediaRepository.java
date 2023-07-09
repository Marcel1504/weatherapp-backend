package me.marcelberger.weatherapp.core.repository.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationMediaRepository extends JpaRepository<StationMediaEntity, Long> {

    StationMediaEntity findByNameAndStation(String name, StationEntity station);
}
