package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.weather.day.WeatherDayFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.api.validator.day.NullableDayString;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
@Validated
public class WeatherDayController {

    @Autowired
    private WeatherDayFacade weatherDayFacade;

    @GetMapping("days")
    public ResponseEntity<PageData<WeatherSummaryData>> getDaysOfMonthForStation(
            @RequestParam(name = "year") @YearString String year,
            @RequestParam(name = "month") @MonthString String month,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(weatherDayFacade.getDaysOfMonthForStation(stationCode, month, year));
    }

    @GetMapping("days/all")
    public ResponseEntity<PageData<WeatherSummaryData>> getAllDaysForStation(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(name = "startDay", required = false) @NullableDayString String startDay,
            @RequestParam(name = "endDay", required = false) @NullableDayString String endDay,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(weatherDayFacade.getAllDaysForStation(stationCode, page, size, startDay, endDay, sort));
    }

    @GetMapping("day")
    public ResponseEntity<WeatherSummaryData> getDayForStation(
            @RequestParam(name = "day") @DayString String day,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(weatherDayFacade.getDayForStation(stationCode, day));
    }
}
