package me.marcelberger.weatherapp.api.data.station;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;

import java.io.File;

@Data
@Builder
public class StationMediaData {
    private File file;
    private MediaType mediaType;
}
