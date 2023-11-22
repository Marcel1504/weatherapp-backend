package me.marcelberger.weatherapp.api.controller.summary.hour;

import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.hour.WeatherHourSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.hour.WeatherHourSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.hour.HourSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/hours")
@Validated
@CrossOrigin
public class WeatherHourSummaryController {

    @Autowired
    private HourSummaryFacade<WeatherHourSummaryEntity, WeatherHourSummaryData, WeatherSortEnum> hourDataFacade;

    @GetMapping
    public ResponseEntity<PageData<WeatherHourSummaryData>> getHoursOfDayForStation(
            @RequestParam(value = "day") @DayString String day,
            @RequestParam(name = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(hourDataFacade.getHoursOfDayForStation(stationCode, day, sort));
    }
}
