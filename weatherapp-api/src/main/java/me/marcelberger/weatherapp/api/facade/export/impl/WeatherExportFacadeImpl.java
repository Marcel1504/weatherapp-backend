package me.marcelberger.weatherapp.api.facade.export.impl;

import me.marcelberger.weatherapp.api.facade.export.ExportFacade;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherExportFacadeImpl extends ExportFacade<WeatherDaySummaryEntity> {
}
