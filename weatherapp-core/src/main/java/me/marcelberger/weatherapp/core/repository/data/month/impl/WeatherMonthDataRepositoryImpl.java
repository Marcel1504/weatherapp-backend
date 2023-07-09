package me.marcelberger.weatherapp.core.repository.data.month.impl;

import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
import me.marcelberger.weatherapp.core.repository.data.month.MonthDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherMonthDataRepositoryImpl extends MonthDataRepository<WeatherMonthDataEntity>, JpaRepository<WeatherMonthDataEntity, Long> {
}
