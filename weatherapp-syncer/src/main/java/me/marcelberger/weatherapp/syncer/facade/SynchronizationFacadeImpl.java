package me.marcelberger.weatherapp.syncer.facade;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.syncer.service.synchronization.SynchronizationService;
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
    private StationRepository stationRepository;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    @Qualifier("currentRunningSynchronizationTaskIds")
    private List<Long> currentRunningSynchronizationTaskIds;

    @Override
    public StatusData syncFullForStationByCode(String stationCode) {
        return syncForStationByCode(stationCode, false);
    }

    @Override
    public StatusData syncFullForAllStations() {
        return syncForAllStations(false);
    }

    @Override
    public StatusData syncDeltaForStationByCode(String stationCode) {
        return syncForStationByCode(stationCode, true);
    }

    @Override
    public StatusData syncDeltaForAllStations() {
        return syncForAllStations(true);
    }

    private StatusData syncForStationByCode(String stationCode, boolean syncDelta) {
        return startSynchronizationTask((id) -> {
            StationEntity station = getStationByCode(stationCode);
            synchronizationServices.stream()
                    .filter(s -> s.getStationType() == station.getType())
                    .findFirst()
                    .ifPresent(s -> s.syncForStation(station, syncDelta));
        });
    }

    private StatusData syncForAllStations(boolean syncDelta) {
        return startSynchronizationTask((id) -> getAllStations()
                .forEach(station -> synchronizationServices.stream()
                        .filter(s -> s.getStationType() == station.getType())
                        .findFirst()
                        .ifPresent(s -> s.syncForStation(station, syncDelta))));
    }

    private StatusData startSynchronizationTask(Consumer<Long> action) {
        if (currentRunningSynchronizationTaskIds.isEmpty()) {
            taskExecutor.execute(getSynchronizationTask(action));
            return StatusData.builder()
                    .message("Started synchronization")
                    .build();
        } else {
            throw new ServiceException("Cannot synchronize: Another synchronization is currently running");
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

    private StationEntity getStationByCode(String stationCode) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null || station.getType() == null) {
            throw new ServiceException("Station %s was not found or has no type", stationCode);
        }
        return station;
    }

    private List<StationEntity> getAllStations() {
        return stationRepository.findAll().stream().filter(s -> s.getType() != null).toList();
    }
}
