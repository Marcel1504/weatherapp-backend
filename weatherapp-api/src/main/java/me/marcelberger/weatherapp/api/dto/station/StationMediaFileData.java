package me.marcelberger.weatherapp.api.dto.station;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;

import java.io.File;

@Data
@Builder
public class StationMediaFileData {
    private File file;
    private MediaType mediaType;
}
