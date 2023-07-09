package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.weather.WeatherData;
import me.marcelberger.weatherapp.api.facade.weather.WeatherFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherFacade weatherFacade;

    @GetMapping("/latest")
    public ResponseEntity<WeatherData> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(weatherFacade.getLatest(stationCode));
    }
}
