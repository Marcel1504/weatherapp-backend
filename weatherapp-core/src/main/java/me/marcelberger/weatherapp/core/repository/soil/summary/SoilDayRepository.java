package me.marcelberger.weatherapp.core.repository.soil.summary;

import me.marcelberger.weatherapp.core.entity.soil.summary.SoilDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilDayRepository extends JpaRepository<SoilDayEntity, Long> {
}
