package me.marcelberger.weatherapp.consumer.cronjob;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.service.diagnosis.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DiagnosisCronJob {

    @Autowired
    private DiagnosisService diagnosisService;

    @Scheduled(initialDelayString = "${weatherapp.station.diagnosis.initialDelayMillis}",
            fixedDelayString = "${weatherapp.station.diagnosis.fixedDelayMillis}")
    public void execute() {
        diagnosisService.checkActivityOfAllStations();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        diagnosisService.markStartupInactivityForAllStations();
    }
}
