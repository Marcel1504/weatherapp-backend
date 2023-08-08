package me.marcelberger.weatherapp.consumer.service.media;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.entity.station.StationMediaEntity;
import me.marcelberger.weatherapp.core.repository.station.StationMediaRepository;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Value("${weatherapp.station.media.directory.source}")
    private String sourceDirectory;

    @Value("${weatherapp.station.media.directory.target}")
    private String targetDirectory;

    @Value("${weatherapp.station.media.latestKey}")
    private String latestMediaKey;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMediaRepository stationMediaRepository;

    @Autowired
    private FileService fileService;

    @Override
    public void consumeLatestMediaForAllStations() {
        stationRepository.findAll().forEach(this::consumeLatestMediaForStation);
    }

    @Override
    public void consumeReviewMediaForAllStations() {
        stationRepository.findAll().forEach(this::consumeReviewMediaForStation);
    }

    private void consumeLatestMediaForStation(StationEntity station) {
        if (station != null && station.getCode() != null) {
            String source = String.format("%s/%s", sourceDirectory, station.getCode());
            File latest = fileService.getLatestFileInDirectoryByContentType(source, "image/jpeg");
            if (latest != null) {
                String filename = String.format("%s-%s.jpg", station.getCode(), latestMediaKey);
                String target = String.format("%s/%s", targetDirectory, filename);
                fileService.copyFile(latest, target);
                saveStationMedia(station, latestMediaKey, filename);
            }
            fileService.cleanDirectory(source);
        }
    }

    private void consumeReviewMediaForStation(StationEntity station) {
        if (station != null && station.getCode() != null) {
            StationMediaEntity media = stationMediaRepository.findByNameAndStation(latestMediaKey, station);
            if (media != null && !media.getCreationTimestamp().isBefore(LocalDateTime.now().minusHours(1))) {
                String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                String targetFilename = String.format(
                        "%s-%s.jpg",
                        station.getCode(),
                        date);
                String targetPath = String.format(
                        "%s/%s",
                        targetDirectory, targetFilename);
                String sourcePath = String.format("%s/%s", targetDirectory, media.getPath());
                fileService.copyFile(Path.of(sourcePath).toFile(), targetPath);
                saveStationMedia(station, date, targetFilename);
            }
        }
    }

    private void saveStationMedia(StationEntity station, String name, String filename) {
        StationMediaEntity media = stationMediaRepository.findByNameAndStation(name, station);
        if (media == null) {
            media = StationMediaEntity.builder()
                    .station(station)
                    .name(name)
                    .build();
        }
        media.setPath(filename);
        media.setCreationTimestamp(LocalDateTime.now());
        stationMediaRepository.save(media);
    }
}
