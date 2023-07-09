package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.data.station.StationMediaData;
import me.marcelberger.weatherapp.api.mapper.DataMapper;
import me.marcelberger.weatherapp.api.service.file.FileService;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StationMediaDataMapperImpl implements DataMapper<StationMediaEntity, StationMediaData> {

    @Autowired
    private FileService fileService;

    @Value("${weatherapp.station.media.directory}")
    private String stationMediaDirectory;

    @Override
    public StationMediaData map(StationMediaEntity object) {
        File file = fileService.getFile(String.format("%s/%s", stationMediaDirectory, object.getPath()));
        return StationMediaData.builder()
                .mediaType(MediaType.parseMediaType(fileService.getContentType(file)))
                .file(file)
                .build();
    }
}
