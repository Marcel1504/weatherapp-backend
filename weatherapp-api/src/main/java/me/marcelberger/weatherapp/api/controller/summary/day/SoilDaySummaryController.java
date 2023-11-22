package me.marcelberger.weatherapp.api.controller.summary.day;

import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.api.validator.day.NullableDayString;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.day.SoilDaySummaryData;
import me.marcelberger.weatherapp.core.entity.summary.day.SoilDaySummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.day.DaySummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil")
@Validated
@CrossOrigin
public class SoilDaySummaryController {

    @Autowired
    private DaySummaryFacade<SoilDaySummaryEntity, SoilDaySummaryData, SoilSortEnum> dayFacade;

    @GetMapping("days")
    public ResponseEntity<PageData<SoilDaySummaryData>> getDaysOfMonth(
            @RequestParam(name = "year") @YearString String year,
            @RequestParam(name = "month") @MonthString String month,
            @RequestParam(name = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getDaysOfMonthForStation(stationCode, month, year, sort));
    }

    @GetMapping("days/all")
    public ResponseEntity<PageData<SoilDaySummaryData>> getAllDays(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(name = "startDay", required = false) @NullableDayString String startDay,
            @RequestParam(name = "endDay", required = false) @NullableDayString String endDay,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getAllDaysForStation(stationCode, page, size, startDay, endDay, sort));
    }

    @GetMapping("day")
    public ResponseEntity<SoilDaySummaryData> getSoilDay(
            @RequestParam(name = "day") @DayString String day,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getDayForStation(stationCode, day));
    }
}
