package me.marcelberger.weatherapp.core.repository.weather.summary;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherMonthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherMonthRepository extends JpaRepository<WeatherMonthEntity, Long> {
    WeatherMonthEntity findByStationAndMonthAndYear(StationEntity station, String month, String year);
}
