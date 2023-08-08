package me.marcelberger.weatherapp.consumer.service.diagnosis;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MailService mailService;

    @Value("${weatherapp.station.diagnosis.adminMail}")
    private String adminMail;

    @Override
    public void checkActivityOfAllStations() {
        stationRepository.findAll().forEach(this::checkActivityOfStation);
    }

    @Override
    public void markStartupInactivityForAllStations() {
        stationRepository.findAll().forEach(s -> {
            s.setInactivityIssueActive(true);
            stationRepository.save(s);
        });
    }

    private void checkActivityOfStation(StationEntity station) {
        if (station == null || station.getDisabled()) {
            return;
        }

        LocalDateTime lastActivity = station.getLastActivity();
        int threshold = station.getInactivityThresholdHours();

        if (lastActivity.isBefore(LocalDateTime.now().minusHours(threshold))) {
            if (!station.getInactivityIssueActive()) {
                sendInactivityMailForStation(station);
            }
            station.setInactivityIssueActive(true);
        } else {
            station.setInactivityIssueActive(false);
        }
        stationRepository.save(station);
    }

    private void sendInactivityMailForStation(StationEntity station) {
        String responsibleMail = station.getResponsibleEmail();
        String subject = String.format("Station %s inactive", station.getCode());
        String text = String.format(
                "Station %s (%s) is inactive since %s hours.\nLast activity was at %s",
                station.getCode(),
                station.getName(),
                station.getInactivityThresholdHours(),
                station.getLastActivity().format(DateTimeFormatter.ISO_DATE_TIME));
        if (responsibleMail != null) {
            mailService.sendMail(responsibleMail, subject, text);
        }
        if (adminMail != null) {
            mailService.sendMail(adminMail, subject, text);
        }
        log.info("Station {} is inactive since {} hours, sending info mails",
                station.getCode(),
                station.getInactivityThresholdHours().toString());
    }
}
