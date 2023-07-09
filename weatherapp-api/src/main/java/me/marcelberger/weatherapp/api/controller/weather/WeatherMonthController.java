package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.month.WeatherMonthDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.month.MonthDataFacade;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.NullableYearString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.entity.data.month.WeatherMonthDataEntity;
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
public class WeatherMonthController {

    @Autowired
    private MonthDataFacade<WeatherMonthDataEntity, WeatherMonthDataResponseDto, WeatherSortEnum> monthDataFacade;

    @GetMapping("month")
    public ResponseEntity<WeatherMonthDataResponseDto> getMonthForStation(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "month") @MonthString String month,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthForStation(stationCode, month, year));
    }

    @GetMapping("months/all")
    public ResponseEntity<PageData<WeatherMonthDataResponseDto>> getMonthsOfYearForStation(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "year", required = false) @NullableYearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(monthDataFacade.getMonthsOfYearForStation(stationCode, page, size, year, sort));
    }
}
