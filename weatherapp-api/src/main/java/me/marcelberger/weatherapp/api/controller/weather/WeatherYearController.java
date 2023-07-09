package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.data.PageData;
import me.marcelberger.weatherapp.api.data.weather.WeatherSummaryData;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.weather.year.WeatherYearFacade;
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
public class WeatherYearController {

    @Autowired
    private WeatherYearFacade weatherYearFacade;

    @GetMapping("year")
    public ResponseEntity<WeatherSummaryData> getWeatherYear(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(weatherYearFacade.getYearForStation(stationCode, year));
    }

    @GetMapping("years/all")
    public ResponseEntity<PageData<WeatherSummaryData>> getAllYears(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(weatherYearFacade.getAllYearsForStation(stationCode, page, size, sort));
    }
}
