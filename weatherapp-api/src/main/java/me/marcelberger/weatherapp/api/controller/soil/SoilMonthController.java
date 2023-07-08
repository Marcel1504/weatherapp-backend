package me.marcelberger.weatherapp.api.controller.soil;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.soil.SoilSummaryData;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.NullableYearString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("soil")
@Validated
public class SoilMonthController {

    @GetMapping("month")
    public ResponseEntity<SoilSummaryData> getSoilMonth(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "month") @MonthString String month,
            @RequestParam(value = "station") String stationCode) {
        // TODO: connect
        return null;
    }

    @GetMapping("months/all")
    public ResponseEntity<PageData<SoilSummaryData>> getAllSoilMonths(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(value = "year", required = false) @NullableYearString String year,
            @RequestParam(value = "station") String stationCode) {
        // TODO: connect
        return null;
    }
}
