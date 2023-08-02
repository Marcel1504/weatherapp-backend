package me.marcelberger.weatherapp.api.controller.data.export;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.MailRequestData;
import me.marcelberger.weatherapp.api.facade.data.export.ExportDataFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.core.entity.data.day.WeatherDayDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/export")
@Validated
public class WeatherExportDataController {

    @Autowired
    private ExportDataFacade<WeatherDayDataEntity> exportDataFacade;

    @PostMapping
    public ResponseEntity<StatusData> export(
            @RequestParam("startDay") @DayString String startDay,
            @RequestParam("endDay") @DayString String endDay,
            @RequestParam("station") String stationCode,
            @RequestBody @Valid MailRequestData mail) {
        return ResponseEntity.ok(
                exportDataFacade.exportWithTimeRangeInfo(mail.getEmail(), startDay, endDay, stationCode));
    }
}
