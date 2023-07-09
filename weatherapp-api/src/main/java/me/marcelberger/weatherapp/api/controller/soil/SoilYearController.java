package me.marcelberger.weatherapp.api.controller.soil;

import me.marcelberger.weatherapp.api.dto.PageData;
import me.marcelberger.weatherapp.api.dto.response.data.year.SoilYearDataResponseDto;
import me.marcelberger.weatherapp.api.enumeration.SoilSortEnum;
import me.marcelberger.weatherapp.api.facade.data.year.YearDataFacade;
import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.entity.data.year.SoilYearDataEntity;
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
public class SoilYearController {

    @Autowired
    private YearDataFacade<SoilYearDataEntity, SoilYearDataResponseDto, SoilSortEnum> yearDataFacade;

    @GetMapping("year")
    public ResponseEntity<SoilYearDataResponseDto> getYearForStation(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getYearForStation(stationCode, year));
    }

    @GetMapping("years/all")
    public ResponseEntity<PageData<SoilYearDataResponseDto>> getAllSoilYears(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getAllYearsForStation(stationCode, page, size, sort));
    }
}
