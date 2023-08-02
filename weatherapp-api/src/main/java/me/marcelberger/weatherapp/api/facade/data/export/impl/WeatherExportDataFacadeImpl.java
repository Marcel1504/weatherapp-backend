package me.marcelberger.weatherapp.api.facade.data.export.impl;

import me.marcelberger.weatherapp.api.facade.data.export.ExportDataFacade;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherExportDataFacadeImpl extends ExportDataFacade<WeatherDayDataEntity> {
}
