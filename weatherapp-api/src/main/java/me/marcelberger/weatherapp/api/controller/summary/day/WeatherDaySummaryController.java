package me.marcelberger.weatherapp.api.controller.summary.day;

import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.api.validator.day.NullableDayString;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.day.WeatherDaySummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.day.DaySummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
@Validated
@CrossOrigin
public class WeatherDaySummaryController {

    @Autowired
    private DaySummaryFacade<WeatherDaySummaryEntity, WeatherDaySummaryData, WeatherSortEnum> dayDataFacade;

    @GetMapping("days")
    public ResponseEntity<PageData<WeatherDaySummaryData>> getDaysOfMonthForStation(
            @RequestParam(name = "year") @YearString String year,
            @RequestParam(name = "month") @MonthString String month,
            @RequestParam(name = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayDataFacade.getDaysOfMonthForStation(stationCode, month, year, sort));
    }

    @GetMapping("days/all")
    public ResponseEntity<PageData<WeatherDaySummaryData>> getAllDaysForStation(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(name = "startDay", required = false) @NullableDayString String startDay,
            @RequestParam(name = "endDay", required = false) @NullableDayString String endDay,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayDataFacade.getAllDaysForStation(stationCode, page, size, startDay, endDay, sort));
    }

    @GetMapping("day")
    public ResponseEntity<WeatherDaySummaryData> getDayForStation(
            @RequestParam(name = "day") @DayString String day,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayDataFacade.getDayForStation(stationCode, day));
    }
}
