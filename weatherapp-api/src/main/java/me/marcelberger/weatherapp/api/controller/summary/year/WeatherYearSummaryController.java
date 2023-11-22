package me.marcelberger.weatherapp.api.controller.summary.year;

import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.year.WeatherYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.WeatherYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.WeatherSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
@Validated
@CrossOrigin
public class WeatherYearSummaryController {

    @Autowired
    private YearSummaryFacade<WeatherYearSummaryEntity, WeatherYearSummaryData, WeatherSortEnum> yearDataFacade;

    @GetMapping("year")
    public ResponseEntity<WeatherYearSummaryData> getWeatherYear(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getYearForStation(stationCode, year));
    }

    @GetMapping("years/all")
    public ResponseEntity<PageData<WeatherYearSummaryData>> getAllYears(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getAllYearsForStation(stationCode, page, size, sort));
    }
}
