package me.marcelberger.weatherapp.core.repository.summary.hour.impl;

import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.hour.HourSummaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherHourSummaryRepositoryImpl extends HourSummaryRepository<WeatherHourSummaryEntity>, JpaRepository<WeatherHourSummaryEntity, Long> {
}
