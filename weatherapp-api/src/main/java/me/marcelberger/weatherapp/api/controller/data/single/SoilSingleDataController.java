package me.marcelberger.weatherapp.api.controller.data.single;

import me.marcelberger.weatherapp.api.dto.response.data.single.SoilSingleDataResponseDto;
import me.marcelberger.weatherapp.api.facade.data.single.SingleDataFacade;
import me.marcelberger.weatherapp.core.entity.data.single.SoilSingleDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("soil")
@CrossOrigin
public class SoilSingleDataController {

    @Autowired
    private SingleDataFacade<SoilSingleDataEntity, SoilSingleDataResponseDto> singleDataFacade;

    @GetMapping("/latest")
    public ResponseEntity<SoilSingleDataResponseDto> getLatest(@RequestParam("station") String stationCode) {
        return ResponseEntity.ok(singleDataFacade.getLatest(stationCode));
    }
}
