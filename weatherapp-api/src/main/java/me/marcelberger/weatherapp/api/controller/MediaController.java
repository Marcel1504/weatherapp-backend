package me.marcelberger.weatherapp.api.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("media")
public class MediaController {

    @GetMapping
    public ResponseEntity<FileSystemResource> getMediaFile(@RequestParam(value = "name") String mediaName) {
        /*MediaFileData mediaFile = mediaService.getMediaFile(mediaName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaFile.getMediaType());

        return new ResponseEntity<>(
                new FileSystemResource(mediaFile.getFile()),
                headers,
                HttpStatus.OK
        );*/
        // TODO connect
        return null;
    }
}
