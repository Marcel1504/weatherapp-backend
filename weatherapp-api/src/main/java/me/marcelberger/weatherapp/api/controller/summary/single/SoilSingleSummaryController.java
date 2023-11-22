package me.marcelberger.weatherapp.api.controller.summary.single;

import me.marcelberger.weatherapp.core.data.summary.single.SoilSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.SoilSingleSummaryEntity;
import me.marcelberger.weatherapp.core.facade.summary.single.SingleSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil")
@CrossOrigin
public class SoilSingleSummaryController {

    @Autowired
    private SingleSummaryFacade<SoilSingleSummaryEntity, SoilSingleSummaryData> singleDataFacade;

    @GetMapping("/latest")
    public ResponseEntity<SoilSingleSummaryData> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(singleDataFacade.getLatest(stationCode));
    }
}
