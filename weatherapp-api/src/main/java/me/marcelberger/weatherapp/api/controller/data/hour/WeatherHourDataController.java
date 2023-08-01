package me.marcelberger.weatherapp.api.controller.data.hour;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.hour.WeatherHourDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.WeatherSortEnum;
import me.marcelberger.weatherapp.api.facade.data.hour.HourDataFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.entity.data.hour.WeatherHourDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/hours")
@Validated
@CrossOrigin
public class WeatherHourDataController {

    @Autowired
    private HourDataFacade<WeatherHourDataEntity, WeatherHourDataResponseDto, WeatherSortEnum> hourDataFacade;

    @GetMapping
    public ResponseEntity<PageData<WeatherHourDataResponseDto>> getHoursOfDayForStation(
            @RequestParam(value = "day") @DayString String day,
            @RequestParam(name = "sort", defaultValue = WeatherSortEnum.LATEST_VALUE) WeatherSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(hourDataFacade.getHoursOfDayForStation(stationCode, day, sort));
    }
}
