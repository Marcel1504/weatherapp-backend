package me.marcelberger.weatherapp.api.service.export.executor.impl;

import me.marcelberger.weatherapp.api.service.export.executor.ExportExecutorService;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import org.springframework.stereotype.Service;

@Service
public class SoilExportExecutorServiceImpl extends ExportExecutorService<SoilDaySummaryEntity> {
    @Override
    protected String getSubject() {
        return "export.soil.mail.subject";
    }

    @Override
    protected String getSubjectSingleDay() {
        return "export.soil.mail.subject.single";
    }

    @Override
    protected String getText() {
        return "export.soil.mail.text";
    }

    @Override
    protected String getTextSingleDay() {
        return "export.soil.mail.text.single";
    }
}
