package me.marcelberger.weatherapp.api.service.file;

import java.io.File;

public interface FileService {

    File getFile(String absoluteFilePath);

    String getContentType(File file);

    boolean makeDirectories(String absoluteFilePath);

    void deleteFile(String absoluteFilePath);
}
