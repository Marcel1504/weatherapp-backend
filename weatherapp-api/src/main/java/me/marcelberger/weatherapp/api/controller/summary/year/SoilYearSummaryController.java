package me.marcelberger.weatherapp.api.controller.summary.year;

import me.marcelberger.weatherapp.api.validator.year.YearString;
import me.marcelberger.weatherapp.core.data.PageData;
import me.marcelberger.weatherapp.core.data.summary.year.SoilYearSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.year.SoilYearSummaryEntity;
import me.marcelberger.weatherapp.core.enumeration.sort.SoilSortEnum;
import me.marcelberger.weatherapp.core.facade.summary.year.YearSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil")
@Validated
@CrossOrigin
public class SoilYearSummaryController {

    @Autowired
    private YearSummaryFacade<SoilYearSummaryEntity, SoilYearSummaryData, SoilSortEnum> yearDataFacade;

    @GetMapping("year")
    public ResponseEntity<SoilYearSummaryData> getYearForStation(
            @RequestParam(value = "year") @YearString String year,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getYearForStation(stationCode, year));
    }

    @GetMapping("years/all")
    public ResponseEntity<PageData<SoilYearSummaryData>> getAllSoilYears(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = SoilSortEnum.LATEST_VALUE) SoilSortEnum sort,
            @RequestParam(value = "station") String stationCode) {
        return ResponseEntity.ok(yearDataFacade.getAllYearsForStation(stationCode, page, size, sort));
    }
}
