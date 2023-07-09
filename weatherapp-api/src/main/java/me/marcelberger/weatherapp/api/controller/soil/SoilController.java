package me.marcelberger.weatherapp.api.controller.soil;

import me.marcelberger.weatherapp.api.dto.response.data.single.SoilSingleDataResponseDto;
import me.marcelberger.weatherapp.api.facade.data.single.SingleDataFacade;
import me.marcelberger.weatherapp.core.entity.data.single.SoilDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("soil")
public class SoilController {

    @Autowired
    private SingleDataFacade<SoilDataEntity, SoilSingleDataResponseDto> singleDataFacade;

    @GetMapping("/latest")
    public ResponseEntity<SoilSingleDataResponseDto> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(singleDataFacade.getLatest(stationCode));
    }
}
