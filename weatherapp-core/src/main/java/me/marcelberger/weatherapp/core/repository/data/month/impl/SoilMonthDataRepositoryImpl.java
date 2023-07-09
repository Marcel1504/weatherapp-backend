package me.marcelberger.weatherapp.core.repository.data.month.impl;

import me.marcelberger.weatherapp.core.entity.data.month.SoilMonthDataEntity;
import me.marcelberger.weatherapp.core.repository.data.month.MonthDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilMonthDataRepositoryImpl extends MonthDataRepository<SoilMonthDataEntity>, JpaRepository<SoilMonthDataEntity, Long> {
}
