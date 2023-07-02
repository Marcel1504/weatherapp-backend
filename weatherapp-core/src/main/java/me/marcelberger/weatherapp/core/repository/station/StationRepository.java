package me.marcelberger.weatherapp.core.repository.station;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {

    StationEntity findByApiKey(String apiKey);

    StationEntity findByCode(String code);
}
