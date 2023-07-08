package me.marcelberger.weatherapp.api.controller;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.StationData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("station")
public class StationController {

    @GetMapping("/all")
    public ResponseEntity<PageData<StationData>> getAll() {
        //TODO: connect
        return null;
    }

    @GetMapping
    public ResponseEntity<StationData> getByStationCode(@RequestParam(name = "station") String stationCode) {
        //TODO: connect
        return null;
    }
}
