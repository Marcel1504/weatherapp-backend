package me.marcelberger.weatherapp.api.mapper.station;

import me.marcelberger.weatherapp.api.dto.response.station.StationMediaFileResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.api.service.file.FileService;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StationMediaFileMapperImpl implements Mapper<StationMediaEntity, StationMediaFileResponseDto> {

    @Autowired
    private FileService fileService;

    @Value("${weatherapp.station.media.directory}")
    private String mediaDirectory;

    @Override
    public StationMediaFileResponseDto map(StationMediaEntity object) {
        File file = fileService.getFile(String.format("%s/%s", mediaDirectory, object.getPath()));
        return StationMediaFileResponseDto.builder()
                .mediaType(MediaType.parseMediaType(fileService.getContentType(file)))
                .file(file)
                .build();
    }
}
