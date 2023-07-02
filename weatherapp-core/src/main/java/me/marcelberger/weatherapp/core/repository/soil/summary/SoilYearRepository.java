package me.marcelberger.weatherapp.core.repository.soil.summary;

import me.marcelberger.weatherapp.core.entity.soil.summary.SoilYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilYearRepository extends JpaRepository<SoilYearEntity, Long> {
}
