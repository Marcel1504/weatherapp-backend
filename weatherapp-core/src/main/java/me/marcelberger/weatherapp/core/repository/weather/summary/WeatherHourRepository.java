package me.marcelberger.weatherapp.core.repository.weather.summary;

import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.weather.summary.WeatherHourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherHourRepository extends JpaRepository<WeatherHourEntity, Long> {
    WeatherHourEntity findByStationAndDayAndHour(StationEntity station, String day, String hour);

    Page<WeatherHourEntity> findAllByStationAndDay(Pageable pageable, StationEntity station, String day);
}
