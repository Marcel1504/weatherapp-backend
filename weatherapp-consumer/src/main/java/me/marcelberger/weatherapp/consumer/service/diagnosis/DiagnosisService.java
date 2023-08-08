package me.marcelberger.weatherapp.consumer.service.diagnosis;

public interface DiagnosisService {
    void checkActivityOfAllStations();

    void markStartupInactivityForAllStations();
}
