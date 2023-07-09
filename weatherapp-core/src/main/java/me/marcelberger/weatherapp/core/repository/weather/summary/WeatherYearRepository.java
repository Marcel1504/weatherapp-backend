package me.marcelberger.weatherapp.core.repository.weather.summary;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherYearEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherYearRepository extends JpaRepository<WeatherYearEntity, Long> {
    WeatherYearEntity findByStationAndYear(StationEntity station, String year);

    Page<WeatherYearEntity> findAllByStation(Pageable pageable, StationEntity station);
}
