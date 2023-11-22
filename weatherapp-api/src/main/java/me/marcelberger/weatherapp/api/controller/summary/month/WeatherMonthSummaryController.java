package me.marcelberger.weatherapp.api.controller.summary.month;


import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.NullableYearString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.month.WeatherMonthSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.month.WeatherMonthSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.month.MonthSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
@Validated
@CrossOrigin
public class WeatherMonthSummaryController {

    @Autowired
    private MonthSummaryFacade<WeatherMonthSummaryEntity, WeatherMonthSummaryData, WeatherSortEnum> monthDataFacade;

    @GetMapping("month")
    public ResponseEntity<WeatherMonthSummaryData> getMonthForStation(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "month") @MonthString String month,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthForStation(stationCode, month, year));
    }

    @GetMapping("months/all")
    public ResponseEntity<PageData<WeatherMonthSummaryData>> getMonthsOfYearForStation(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "year", required = false) @NullableYearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthsOfYearForStation(stationCode, page, size, year, sort));
    }
}
