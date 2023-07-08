package me.marcelberger.weatherapp.api.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;

import java.io.File;

@Data
@Builder
public class MediaFileData {
    private File file;
    private MediaType mediaType;
}
