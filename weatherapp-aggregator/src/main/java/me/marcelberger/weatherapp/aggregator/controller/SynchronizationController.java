package me.marcelberger.weatherapp.aggregator.controller;

import me.marcelberger.weatherapp.aggregator.facade.SynchronizationFacade;
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
    public ResponseEntity<String> syncFullForAll() {
        return ResponseEntity.ok(synchronizationFacade.syncFullForAllStations());
    }

    @GetMapping("full")
    public ResponseEntity<String> syncFullForStationByCode(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(synchronizationFacade.syncFullForStationByCode(stationCode));
    }

    @GetMapping("delta/all")
    public ResponseEntity<String> syncDeltaForAll() {
        return ResponseEntity.ok(synchronizationFacade.syncDeltaForAllStations());
    }

    @GetMapping("delta")
    public ResponseEntity<String> syncDeltaForStationByCode(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(synchronizationFacade.syncDeltaForStationByCode(stationCode));
    }
}
