package me.marcelberger.weatherapp.api.controller.soil;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.day.SoilDayDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.facade.data.day.DayDataFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.api.validator.day.NullableDayString;
import me.marcelberger.weatherapp.api.validator.month.MonthString;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("soil")
@Validated
public class SoilDayController {

    @Autowired
    private DayDataFacade<SoilDayDataEntity, SoilDayDataResponseDto, SoilSortEnum> dayFacade;

    @GetMapping("days")
    public ResponseEntity<PageData<SoilDayDataResponseDto>> getDaysOfMonth(
            @RequestParam(name = "year") @YearString String year,
            @RequestParam(name = "month") @MonthString String month,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getDaysOfMonthForStation(stationCode, month, year));
    }

    @GetMapping("days/all")
    public ResponseEntity<PageData<SoilDayDataResponseDto>> getAllDays(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(name = "startDay", required = false) @NullableDayString String startDay,
            @RequestParam(name = "endDay", required = false) @NullableDayString String endDay,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getAllDaysForStation(stationCode, page, size, startDay, endDay, sort));
    }

    @GetMapping("day")
    public ResponseEntity<SoilDayDataResponseDto> getSoilDay(
            @RequestParam(name = "day") @DayString String day,
            @RequestParam(name = "station") String stationCode) {
        return ResponseEntity.ok(dayFacade.getDayForStation(stationCode, day));
    }
}
