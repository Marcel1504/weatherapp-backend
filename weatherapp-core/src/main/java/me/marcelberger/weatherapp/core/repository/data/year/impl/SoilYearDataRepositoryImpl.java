package me.marcelberger.weatherapp.core.repository.data.year.impl;

import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
import me.marcelberger.weatherapp.core.repository.data.year.YearDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilYearDataRepositoryImpl extends YearDataRepository<SoilYearDataEntity>, JpaRepository<SoilYearDataEntity, Long> {
}
