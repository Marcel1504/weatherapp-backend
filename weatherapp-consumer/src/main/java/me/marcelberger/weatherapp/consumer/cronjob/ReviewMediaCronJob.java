package me.marcelberger.weatherapp.consumer.cronjob;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.service.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReviewMediaCronJob {

    @Autowired
    private MediaService mediaService;

    @Scheduled(cron = "${weatherapp.station.media.cronjob.review}")
    public void run() {
        mediaService.consumeReviewMediaForAllStations();
    }
}