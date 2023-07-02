package me.marcelberger.weatherapp.receiver.controller.data;

import me.marcelberger.weatherapp.core.data.StatusData;
import me.marcelberger.weatherapp.receiver.data.request.SOPRequestData;
import me.marcelberger.weatherapp.receiver.data.request.WEPRequestData;
import me.marcelberger.weatherapp.receiver.facade.data.impl.SOPDataFacadeImpl;
import me.marcelberger.weatherapp.receiver.facade.data.impl.WEPDataFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@Deprecated(forRemoval = true)
public class DataLegacyController {

    @Autowired
    private SOPDataFacadeImpl sopDataFacade;

    @Autowired
    private WEPDataFacadeImpl wepDataFacade;

    /*@Autowired
    private WeWeatherCamService weatherCamService;*/

    @PutMapping("soil/synchronize")
    public ResponseEntity<StatusData> updateFromSoilProducer(
            Principal principal,
            @RequestBody List<SOPRequestData> data) {
        return ResponseEntity.ok(sopDataFacade.updateWithStationCodeFromPrincipalName(principal, data.get(0)));
    }

    @PutMapping("weather/synchronize")
    public ResponseEntity<StatusData> updateFromWeatherProducer(
            Principal principal,
            @RequestBody List<WEPRequestData> data) {
        return ResponseEntity.ok(wepDataFacade.updateWithStationCodeFromPrincipalName(principal, data.get(0)));
    }

    /*
    @PutMapping
    public ResponseEntity<CnStatusModel> updateWeatherCamPicture(
            Principal principal,
            @RequestParam("file") MultipartFile file,
            @RequestParam("creationTimestamp")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationTimestamp) {
        return ResponseEntity.ok(weatherCamService.updateWeatherCam(file, principal, creationTimestamp));
    }*/
}
