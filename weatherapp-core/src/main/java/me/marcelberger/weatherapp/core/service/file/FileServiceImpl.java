package me.marcelberger.weatherapp.core.service.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public File getFile(String absoluteFilePath) {
        if (absoluteFilePath == null) {
            log.warn("Could not get file: path was not provided");
            return null;
        }
        if (!Files.exists(Paths.get(absoluteFilePath))) {
            log.warn("Could not get file {}: file does not exist", absoluteFilePath);
            return null;
        }
        return new File(absoluteFilePath);
    }

    @Override
    public String getContentType(File file) {
        if (file == null) {
            log.warn("Could not determine content type: file was not provided");
            return null;
        }
        try {
            Path path = file.toPath();
            return Files.probeContentType(path);
        } catch (IOException e) {
            log.warn("Could not determine content type for file {}: {}", file.getName(), e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteFile(String absoluteFilePath) {
        if (absoluteFilePath == null || absoluteFilePath.isBlank()) {
            log.warn("Could not delete file since file path was not provided");
            return;
        }
        try {
            FileUtils.forceDelete(Path.of(absoluteFilePath).toFile());
        } catch (IOException e) {
            log.warn("Could not delete file {}: {}", absoluteFilePath, e.getMessage());
        }
    }

    @Override
    public void cleanDirectory(String absoluteFilePath) {
        if (absoluteFilePath == null) {
            log.warn("Could not delete directory: directory path was not provided");
            return;
        }
        Path directory = Path.of(absoluteFilePath);
        if (!Files.isDirectory(directory)) {
            log.warn("Could not delete directory {}: path is not a directory", absoluteFilePath);
            return;
        }
        try {
            FileUtils.cleanDirectory(directory.toFile());
        } catch (IOException e) {
            log.warn("Could not delete directory {}: {}", absoluteFilePath, e.getMessage());
        }
    }

    @Override
    public File getLatestFileInDirectoryByContentType(String absoluteFilePath, String contentType) {
        if (absoluteFilePath == null || contentType == null) {
            log.warn("Could not get latest file: directory path or content type was not provided");
            return null;
        }
        Path directory = Path.of(absoluteFilePath);
        if (!Files.isDirectory(directory)) {
            log.warn("Could not get latest file: path {} is not a directory", absoluteFilePath);
            return null;
        }
        try (Stream<Path> paths = Files.walk(directory)) {
            Path latest = paths
                    .filter(p -> !Files.isDirectory(p))
                    .filter(p -> contentType.equals(getContentType(p.toFile())))
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()))
                    .orElse(null);
            if (latest != null) {
                return latest.toFile();
            }
        } catch (IOException e) {
            log.warn("Could not get latest file in directory {}: {}", absoluteFilePath, e.getMessage());
        }
        return null;
    }

    @Override
    public void copyFile(File source, String targetAbsoluteFilePath) {
        try {
            if (source == null || targetAbsoluteFilePath == null) {
                log.warn("Could not copy file: source or target path was not provided");
                return;
            }
            Files.copy(source.toPath(), Path.of(targetAbsoluteFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.warn("Could not copy file: {}", e.getMessage());
        }
    }
}
