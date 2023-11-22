package me.marcelberger.weatherapp.core.repository.summary.month.impl;

import me.marcelberger.weatherapp.core.entity.summary.month.SoilMonthSummaryEntity;
import me.marcelberger.weatherapp.core.repository.summary.month.MonthSummaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilMonthSummaryRepositoryImpl extends MonthSummaryRepository<SoilMonthSummaryEntity>, JpaRepository<SoilMonthSummaryEntity, Long> {
}
