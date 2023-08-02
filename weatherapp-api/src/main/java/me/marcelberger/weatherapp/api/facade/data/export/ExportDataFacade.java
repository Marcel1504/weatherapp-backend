package me.marcelberger.weatherapp.api.facade.data.export;

import me.marcelberger.weatherapp.api.service.export.executor.ExportExecutorService;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.data.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class ExportDataFacade<SOURCE> {

    @Autowired
    private ExportExecutorService<SOURCE> executorService;

    @Autowired
    private MessageService msg;

    public StatusData exportWithTimeRangeInfo(
            String email,
            String startDay,
            String endDay,
            String stationCode) {
        executorService.exportWithTimeRangeInfo(email, startDay, endDay, stationCode, LocaleContextHolder.getLocale());
        return StatusData.builder()
                .message(msg.get("export.success"))
                .build();
    }
}