package me.marcelberger.weatherapp.api.service.export.executor.impl;

import me.marcelberger.weatherapp.api.service.export.executor.ExportExecutorService;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherExportExecutorServiceImpl extends ExportExecutorService<WeatherDaySummaryEntity> {
    @Override
    protected String getSubject() {
        return "export.weather.mail.subject";
    }

    @Override
    protected String getSubjectSingleDay() {
        return "export.weather.mail.subject.single";
    }

    @Override
    protected String getText() {
        return "export.weather.mail.text";
    }

    @Override
    protected String getTextSingleDay() {
        return "export.weather.mail.text.single";
    }
}
