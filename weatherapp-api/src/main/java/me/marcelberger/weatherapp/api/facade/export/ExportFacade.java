package me.marcelberger.weatherapp.api.facade.export;

import me.marcelberger.weatherapp.api.service.export.executor.ExportExecutorService;
import me.marcelberger.weatherapp.core.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class ExportFacade<SOURCE> {

    @Autowired
    private ExportExecutorService<SOURCE> executorService;

    @Autowired
    private MessageService msg;

    public String exportWithTimeRangeInfo(
            String email,
            String startDay,
            String endDay,
            String stationCode) {
        executorService.exportWithTimeRangeInfo(email, startDay, endDay, stationCode, LocaleContextHolder.getLocale());
        return msg.get("export.success");
    }
}