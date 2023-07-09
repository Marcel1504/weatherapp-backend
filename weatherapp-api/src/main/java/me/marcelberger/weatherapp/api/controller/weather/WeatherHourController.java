package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.weather.hour.WeatherHourFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WeatherHourFacade weatherHourFacade;

    @GetMapping
    public ResponseEntity<PageData<WeatherSummaryData>> getHoursOfDayForStation(
            @RequestParam(value = "day") @DayString String day,
            @RequestParam(name = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(weatherHourFacade.getHoursOfDayForStation(stationCode, day, sort));
    }
}
