package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @GetMapping("/latest")
    public ResponseEntity<WeatherData> getLatest(@RequestParam("station") String stationCode) {
        // TODO: connect
        return null;
    }
}
