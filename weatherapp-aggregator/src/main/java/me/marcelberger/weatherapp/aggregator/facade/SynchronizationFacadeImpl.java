package me.marcelberger.weatherapp.aggregator.facade;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.aggregator.service.synchronization.SynchronizationService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
public class SynchronizationFacadeImpl implements SynchronizationFacade {

    @Autowired
    private List<SynchronizationService<?>> synchronizationServices;

    @Autowired
    private StationService stationService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    @Qualifier("currentRunningSynchronizationTaskIds")
    private List<Long> currentRunningSynchronizationTaskIds;

    @Override
    public String syncFullForStationByCode(String stationCode) {
        return syncForStationByCode(stationCode, false);
    }

    @Override
    public String syncFullForAllStations() {
        return syncForAllStations(false);
    }

    @Override
    public String syncDeltaForStationByCode(String stationCode) {
        return syncForStationByCode(stationCode, true);
    }

    @Override
    public String syncDeltaForAllStations() {
        return syncForAllStations(true);
    }

    private String syncForStationByCode(String stationCode, boolean syncDelta) {
        return startSynchronizationTask((id) -> {
            StationEntity station = stationService.getByStationCode(stationCode);
            synchronizationServices.stream()
                    .filter(s -> s.getStationType() == station.getType())
                    .findFirst()
                    .ifPresent(s -> s.syncForStation(station, syncDelta));
        });
    }

    private String syncForAllStations(boolean syncDelta) {
        return startSynchronizationTask((id) -> stationService.getAll()
                .forEach(station -> synchronizationServices.stream()
                        .filter(s -> s.getStationType() == station.getType())
                        .findFirst()
                        .ifPresent(s -> s.syncForStation(station, syncDelta))));
    }

    private String startSynchronizationTask(Consumer<Long> action) {
        if (currentRunningSynchronizationTaskIds.isEmpty()) {
            taskExecutor.execute(getSynchronizationTask(action));
            return "Started synchronization";
        } else {
            return "Cannot synchronize: Another synchronization is currently running";
        }
    }

    private Runnable getSynchronizationTask(Consumer<Long> action) {
        return () -> {
            Long id = System.currentTimeMillis();
            try {
                currentRunningSynchronizationTaskIds.add(id);
                action.accept(id);
            } catch (Exception e) {
                log.warn("Synchronization task {} ended unsuccessfully: {}", id, e.getMessage());
            }
            currentRunningSynchronizationTaskIds.remove(id);
        };
    }
}
