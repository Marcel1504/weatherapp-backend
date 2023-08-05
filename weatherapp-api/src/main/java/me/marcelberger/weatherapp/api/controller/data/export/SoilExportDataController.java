package me.marcelberger.weatherapp.api.controller.data.export;

import jakarta.validation.Valid;
import me.marcelberger.weatherapp.api.dto.request.data.export.ExportDataRequestDto;
import me.marcelberger.weatherapp.api.facade.data.export.ExportDataFacade;
import me.marcelberger.weatherapp.api.validator.day.DayString;
import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil/export")
@Validated
@CrossOrigin
public class SoilExportDataController {

    @Autowired
    private ExportDataFacade<SoilDayDataEntity> exportDataFacade;

    @PostMapping
    public ResponseEntity<StatusData> export(
            @RequestParam("startDay") @DayString String startDay,
            @RequestParam("endDay") @DayString String endDay,
            @RequestParam("station") String stationCode,
            @RequestBody @Valid ExportDataRequestDto mail) {
        return ResponseEntity.ok(
                exportDataFacade.exportWithTimeRangeInfo(mail.getEmail(), startDay, endDay, stationCode));
    }
}
