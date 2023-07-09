package me.marcelberger.weatherapp.api.controller.station;

import me.marcelberger.weatherapp.api.data.station.StationMediaData;
import me.marcelberger.weatherapp.api.facade.station.media.StationMediaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("station/media")
public class StationMediaController {

    @Autowired
    private StationMediaFacade stationMediaFacade;

    @GetMapping
    public ResponseEntity<FileSystemResource> getStationMediaFile(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "station") String stationCode) {
        StationMediaData mediaFile = stationMediaFacade.getStationMedia(name, stationCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaFile.getMediaType());
        return new ResponseEntity<>(
                new FileSystemResource(mediaFile.getFile()),
                headers,
                HttpStatus.OK
        );
    }
}
