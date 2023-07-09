package me.marcelberger.weatherapp.core.repository.data.hour.impl;

import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import me.marcelberger.weatherapp.core.repository.data.hour.HourDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherHourDataRepositoryImpl extends HourDataRepository<WeatherHourDataEntity>, JpaRepository<WeatherHourDataEntity, Long> {
}
