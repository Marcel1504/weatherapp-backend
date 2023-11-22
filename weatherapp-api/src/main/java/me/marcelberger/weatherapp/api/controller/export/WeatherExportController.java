package me.marcelberger.weatherapp.api.controller.export;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.ExportDataRequestDto;
import me.marcelberger.weatherapp.api.facade.export.ExportFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/export")
@Validated
@CrossOrigin
public class WeatherExportController {

    @Autowired
    private ExportFacade<WeatherDaySummaryEntity> exportFacade;

    @PostMapping
    public ResponseEntity<String> export(
            @RequestParam("startDay") @DayString String startDay,
            @RequestParam("endDay") @DayString String endDay,
            @RequestParam("station") String stationCode,
            @RequestBody @Valid ExportDataRequestDto mail) {
        return ResponseEntity.ok(exportFacade.exportWithTimeRangeInfo(mail.getEmail(), startDay, endDay, stationCode));
    }
}
