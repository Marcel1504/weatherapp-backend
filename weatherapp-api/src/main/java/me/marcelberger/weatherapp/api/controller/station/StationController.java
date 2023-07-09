package me.marcelberger.weatherapp.api.controller.station;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.station.StationData;
import me.marcelberger.weatherapp.api.facade.station.StationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("station")
public class StationController {

    @Autowired
    private StationFacade stationFacade;

    @GetMapping("/all")
    public ResponseEntity<PageData<StationData>> getAll() {
        return ResponseEntity.ok(stationFacade.getAll());
    }

    @GetMapping
    public ResponseEntity<StationData> getByStationCode(@RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(stationFacade.getByStationCode(stationCode));
    }
}
