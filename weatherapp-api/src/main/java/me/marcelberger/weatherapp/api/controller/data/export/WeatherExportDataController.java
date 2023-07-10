package me.marcelberger.weatherapp.api.controller.data.export;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.MailRequestData;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.data.StatusData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/export")
@Validated
public class WeatherExportDataController {

    @PostMapping
    public ResponseEntity<StatusData> export(
            @RequestParam("startDay") @DayString String startDay,
            @RequestParam("endDay") @DayString String endDay,
            @RequestParam("station") String stationCode,
            @RequestBody @Valid MailRequestData mail) {
        // TODO: connect
        return null;
    }
}
