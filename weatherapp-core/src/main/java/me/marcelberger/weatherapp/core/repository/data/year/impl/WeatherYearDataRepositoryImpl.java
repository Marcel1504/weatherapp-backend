package me.marcelberger.weatherapp.core.repository.data.year.impl;

import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
import me.marcelberger.weatherapp.core.repository.data.year.YearDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherYearDataRepositoryImpl extends YearDataRepository<WeatherYearDataEntity>, JpaRepository<WeatherYearDataEntity, Long> {
}
