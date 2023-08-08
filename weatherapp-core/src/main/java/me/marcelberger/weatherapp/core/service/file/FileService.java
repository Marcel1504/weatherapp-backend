package me.marcelberger.weatherapp.core.service.file;

import java.io.File;

public interface FileService {

    File getFile(String absoluteFilePath);

    String getContentType(File file);

    void deleteFile(String absoluteFilePath);

    void cleanDirectory(String absoluteFilePath);

    File getLatestFileInDirectoryByContentType(String absoluteFilePath, String contentType);

    void copyFile(File source, String targetAbsoluteFilePath);
}
