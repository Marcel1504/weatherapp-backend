package me.marcelberger.weatherapp.api.service.file;

import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private static final String FILE_READ_FAIL_MSG = "file.read.fail";
    @Autowired
    private MessageService msg;

    @Override
    public File getFile(String absoluteFilePath) {
        if (!Files.exists(Paths.get(absoluteFilePath))) {
            throw new ServiceException(msg.get(FILE_READ_FAIL_MSG));
        }
        return new File(absoluteFilePath);
    }

    @Override
    public String getContentType(File file) {
        try {
            Path path = file.toPath();
            return Files.probeContentType(path);
        } catch (Exception e) {
            throw new ServiceException(msg.get(FILE_READ_FAIL_MSG));
        }
    }

    @Override
    public boolean makeDirectories(String absoluteFilePath) {
        return new File(absoluteFilePath).mkdirs();
    }

    @Override
    public void deleteFile(String absoluteFilePath) {
        if (absoluteFilePath == null || absoluteFilePath.isBlank()) {
            throw new ServiceException(msg.get(FILE_READ_FAIL_MSG));
        }
        try {
            Path fileToDeletePath = Paths.get(absoluteFilePath);
            if (Files.exists(fileToDeletePath)) {
                Files.delete(fileToDeletePath);
            }
        } catch (IOException e) {
            throw new ServiceException(msg.get(FILE_READ_FAIL_MSG));
        }
    }
}
