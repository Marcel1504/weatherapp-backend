package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather/hours")
@Validated
public class WeatherHourController {
    @GetMapping
    public ResponseEntity<PageData<WeatherSummaryData>> getHoursOfDay(
            @RequestParam(value = "day") @DayString String day,
            @RequestParam(value = "station") String stationCode) {
        // TODO: connect
        return null;
    }
}
