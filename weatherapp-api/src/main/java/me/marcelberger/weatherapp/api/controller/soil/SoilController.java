package me.marcelberger.weatherapp.api.controller.soil;

import me.marcelberger.weatherapp.api.data.soil.SoilData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("soil")
public class SoilController {

    @GetMapping("/latest")
    public ResponseEntity<SoilData> getLatest(@RequestParam("station") String stationCode) {
        // TODO: connect
        return null;
    }
}
