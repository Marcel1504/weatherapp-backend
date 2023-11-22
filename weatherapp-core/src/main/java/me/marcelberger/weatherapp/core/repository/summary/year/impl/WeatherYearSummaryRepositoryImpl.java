package me.marcelberger.weatherapp.core.repository.summary.year.impl;

import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.year.YearSummaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherYearSummaryRepositoryImpl extends YearSummaryRepository<WeatherYearSummaryEntity>, JpaRepository<WeatherYearSummaryEntity, Long> {
}
