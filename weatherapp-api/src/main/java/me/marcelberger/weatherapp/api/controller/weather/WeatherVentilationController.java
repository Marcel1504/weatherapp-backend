package me.marcelberger.weatherapp.api.controller.weather;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.weather.WeatherVentilationRequestData;
import me.marcelberger.weatherapp.api.dto.weather.WeatherVentilationResultData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather/ventilation")
@Validated
public class WeatherVentilationController {

    @PutMapping
    public ResponseEntity<WeatherVentilationResultData> determineVentilationDemand(
            @RequestBody @Valid WeatherVentilationRequestData request) {
        // TODO: connect
        return null;
    }
}
