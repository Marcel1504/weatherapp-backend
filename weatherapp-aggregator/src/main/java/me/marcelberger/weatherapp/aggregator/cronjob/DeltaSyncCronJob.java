package me.marcelberger.weatherapp.aggregator.cronjob;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.aggregator.facade.SynchronizationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeltaSyncCronJob {

    @Autowired
    private SynchronizationFacade synchronizationFacade;

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        synchronizationFacade.syncDeltaForAllStations();
    }
}
