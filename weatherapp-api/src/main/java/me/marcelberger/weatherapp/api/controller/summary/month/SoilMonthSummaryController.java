package me.marcelberger.weatherapp.api.controller.summary.month;

import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.NullableYearString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.month.SoilMonthSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.month.SoilMonthSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.month.MonthSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil")
@Validated
@CrossOrigin
public class SoilMonthSummaryController {

    @Autowired
    private MonthSummaryFacade<SoilMonthSummaryEntity, SoilMonthSummaryData, SoilSortEnum> monthDataFacade;

    @GetMapping("month")
    public ResponseEntity<SoilMonthSummaryData> getMonthForStation(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "month") @MonthString String month,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthForStation(stationCode, month, year));
    }

    @GetMapping("months/all")
    public ResponseEntity<PageData<SoilMonthSummaryData>> getAllSoilMonths(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(value = "year", required = false) @NullableYearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthsOfYearForStation(stationCode, page, size, year, sort));
    }
}
