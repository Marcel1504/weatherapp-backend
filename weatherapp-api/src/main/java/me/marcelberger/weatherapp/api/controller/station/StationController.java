package me.marcelberger.weatherapp.api.controller.station;

import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.facade.station.StationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("station")
@CrossOrigin
public class StationController {

    @Autowired
    private StationFacade stationFacade;

    @GetMapping("/all")
    public ResponseEntity<PageData<StationData>> getAll() {
        return ResponseEntity.ok(stationFacade.getAllStations());
    }

    @GetMapping
    public ResponseEntity<StationData> getByStationCode(@RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(stationFacade.getStationByCode(stationCode));
    }
}
