package me.marcelberger.weatherapp.api.controller.station;

import me.marcelberger.weatherapp.core.data.station.StationMediaFileData;
import me.marcelberger.weatherapp.core.facade.station.StationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("station/media")
@CrossOrigin
public class StationMediaController {

    @Autowired
    private StationFacade stationFacade;

    @GetMapping
    public ResponseEntity<FileSystemResource> getStationMediaFile(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "station") String stationCode) {
        StationMediaFileData mediaFile = stationFacade.getStationMediaFileByNameAndStationCode(name, stationCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaFile.getMediaType());
        return new ResponseEntity<>(
                new FileSystemResource(mediaFile.getFile()),
                headers,
                HttpStatus.OK
        );
    }
}
