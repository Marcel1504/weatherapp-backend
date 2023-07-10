package me.marcelberger.weatherapp.api.controller.data.year;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.year.WeatherYearDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.year.YearDataFacade;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.entity.data.year.WeatherYearDataEntity;
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
public class WeatherYearDataController {

    @Autowired
    private YearDataFacade<WeatherYearDataEntity, WeatherYearDataResponseDto, WeatherSortEnum> yearDataFacade;

    @GetMapping("year")
    public ResponseEntity<WeatherYearDataResponseDto> getWeatherYear(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getYearForStation(stationCode, year));
    }

    @GetMapping("years/all")
    public ResponseEntity<PageData<WeatherYearDataResponseDto>> getAllYears(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getAllYearsForStation(stationCode, page, size, sort));
    }
}
