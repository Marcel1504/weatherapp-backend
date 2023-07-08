package me.marcelberger.weatherapp.core.repository.soil.summary;

import me.marcelberger.weatherapp.core.entity.soil.summary.SoilMonthEntity;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilMonthRepository extends JpaRepository<SoilMonthEntity, Long> {
    SoilMonthEntity findByStationAndMonthAndYear(StationEntity station, String month, String year);
}
