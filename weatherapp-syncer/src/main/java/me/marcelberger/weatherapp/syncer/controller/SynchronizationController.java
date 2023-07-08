package me.marcelberger.weatherapp.syncer.controller;

import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.syncer.facade.SynchronizationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SynchronizationController {

    @Autowired
    private SynchronizationFacade synchronizationFacade;

    @GetMapping("full/all")
    public ResponseEntity<StatusData> syncFullForAll() {
        return ResponseEntity.ok(synchronizationFacade.syncFullForAllStations());
    }

    @GetMapping("full")
    public ResponseEntity<StatusData> syncFullForStationByCode(@RequestParam("stationCode") String stationCode) {
        return ResponseEntity.ok(synchronizationFacade.syncFullForStationByCode(stationCode));
    }

    @GetMapping("delta/all")
    public ResponseEntity<StatusData> syncDeltaForAll() {
        return ResponseEntity.ok(synchronizationFacade.syncDeltaForAllStations());
    }

    @GetMapping("delta")
    public ResponseEntity<StatusData> syncDeltaForStationByCode(@RequestParam("stationCode") String stationCode) {
        return ResponseEntity.ok(synchronizationFacade.syncDeltaForStationByCode(stationCode));
    }
}
