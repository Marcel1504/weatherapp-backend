package me.marcelberger.weatherapp.api.controller.weather;

import me.marcelberger.weatherapp.api.dto.response.data.single.WeatherSingleDataResponseDto;
import me.marcelberger.weatherapp.api.facade.data.single.SingleDataFacade;
import me.marcelberger.weatherapp.core.entity.data.single.WeatherDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private SingleDataFacade<WeatherDataEntity, WeatherSingleDataResponseDto> singleDataFacade;

    @GetMapping("/latest")
    public ResponseEntity<WeatherSingleDataResponseDto> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(singleDataFacade.getLatest(stationCode));
    }
}
