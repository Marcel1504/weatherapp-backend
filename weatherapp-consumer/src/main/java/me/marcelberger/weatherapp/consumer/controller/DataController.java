package me.marcelberger.weatherapp.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.consumer.data.request.SOPRequestData;
import me.marcelberger.weatherapp.consumer.data.request.WEPRequestData;
import me.marcelberger.weatherapp.consumer.facade.data.impl.ECODataFacadeImpl;
import me.marcelberger.weatherapp.consumer.facade.data.impl.SOPDataFacadeImpl;
import me.marcelberger.weatherapp.consumer.facade.data.impl.WEPDataFacadeImpl;
import me.marcelberger.weatherapp.core.data.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class DataController {

    @Autowired
    private ECODataFacadeImpl ecoDataFacade;

    @Autowired
    private WEPDataFacadeImpl wepDataFacade;

    @Autowired
    private SOPDataFacadeImpl sopDataFacade;

    @PostMapping("data/eco")
    public ResponseEntity<StatusData> updateFromEcowitt(@RequestBody String message) {
        return ResponseEntity.ok(ecoDataFacade.updateWithStationFromData(message));
    }

    @PostMapping("data/wep")
    public ResponseEntity<StatusData> updateFromWeatherProducer(
            Principal principal,
            @RequestBody WEPRequestData data) {
        return ResponseEntity.ok(wepDataFacade.updateWithStationCodeFromPrincipalName(principal, data));
    }

    @PostMapping("data/sop")
    public ResponseEntity<StatusData> updateFromSoilProducer(
            Principal principal,
            @RequestBody SOPRequestData data) {
        return ResponseEntity.ok(sopDataFacade.updateWithStationCodeFromPrincipalName(principal, data));
    }

    @Deprecated
    @PutMapping("soil/synchronize")
    public ResponseEntity<StatusData> updateFromSoilProducerOld(
            Principal principal,
            @RequestBody List<SOPRequestData> data) {
        return ResponseEntity.ok(sopDataFacade.updateWithStationCodeFromPrincipalName(principal, data.get(0)));
    }
}
