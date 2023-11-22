package me.marcelberger.weatherapp.api.controller.summary.single;

import me.marcelberger.weatherapp.core.data.summary.single.WeatherSingleSummaryData;
import me.marcelberger.weatherapp.core.entity.summary.single.WeatherSingleSummaryEntity;
import me.marcelberger.weatherapp.core.facade.summary.single.SingleSummaryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
@CrossOrigin
public class WeatherSingleSummaryController {

    @Autowired
    private SingleSummaryFacade<WeatherSingleSummaryEntity, WeatherSingleSummaryData> singleDataFacade;

    @GetMapping("/latest")
    public ResponseEntity<WeatherSingleSummaryData> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(singleDataFacade.getLatest(stationCode));
    }
}
