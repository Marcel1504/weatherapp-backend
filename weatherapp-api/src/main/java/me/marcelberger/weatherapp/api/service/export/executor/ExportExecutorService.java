package me.marcelberger.weatherapp.api.service.export.executor;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.api.service.export.csv.ExportCsvService;
import me.marcelberger.weatherapp.api.service.message.MessageService;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;
import me.marcelberger.weatherapp.core.repository.station.StationRepository;
import me.marcelberger.weatherapp.core.repository.summary.day.DaySummaryRepository;
import me.marcelberger.weatherapp.core.service.file.FileService;
import me.marcelberger.weatherapp.core.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@EnableAsync
public abstract class ExportExecutorService<SOURCE> {

    @Autowired
    private MessageService msg;

    @Autowired
    private FileService fileService;

    @Autowired
    private MailService mailService;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private ExportCsvService<SOURCE> exportCsvService;

    @Autowired
    private DaySummaryRepository<SOURCE> daySummaryRepository;

    @Value("${weatherapp.export.directory}")
    private String exportMediaDirectory;

    @Async
    public void exportWithTimeRangeInfo(
            String email,
            String startDay,
            String endDay,
            String stationCode,
            Locale locale) {
        StationEntity station = stationRepository.findByCode(stationCode);
        if (station == null) {
            log.error("Could not export CSV to mail {}, station {} not found", email, stationCode);
            return;
        }
        String filename = generateCSVFilename(stationCode, startDay, endDay);
        String absolutePath = String.format("%s/%s", exportMediaDirectory, filename);
        try {
            List<SOURCE> dataList = daySummaryRepository
                    .findAllDaysInRangeForStation(Pageable.unpaged(), station, startDay, endDay)
                    .getContent();
            exportCsvService.generateCSVFile(dataList, absolutePath);
            mailService.sendMail(
                    email,
                    generateMailSubject(station.getName(), startDay, endDay, locale),
                    generateMailText(station.getName(), startDay, endDay, locale),
                    List.of(absolutePath));
        } catch (Exception e) {
            log.error("Could not export CSV to mail {}: {}", email, e.getMessage());
        } finally {
            fileService.deleteFile(absolutePath);
        }
    }

    protected abstract String getSubject();

    protected abstract String getSubjectSingleDay();

    protected abstract String getText();

    protected abstract String getTextSingleDay();

    protected String toReadableDayString(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private String generateCSVFilename(String locationKey, String startDate, String endDate) {
        return String.format("%s-%s-%s-%d.csv", locationKey, startDate, endDate, System.currentTimeMillis());
    }

    private String generateMailSubject(String locationName, String startDate, String endDate, Locale locale) {
        return startDate.equals(endDate)
                ? msg.get(
                getSubjectSingleDay(),
                locale,
                locationName,
                toReadableDayString(startDate))
                : msg.get(
                getSubject(),
                locale,
                locationName,
                toReadableDayString(startDate),
                toReadableDayString(endDate));
    }

    private String generateMailText(String locationName, String startDate, String endDate, Locale locale) {
        return startDate.equals(endDate)
                ? msg.get(
                getTextSingleDay(),
                locale,
                locationName,
                toReadableDayString(startDate))
                : msg.get(
                getText(),
                locale,
                locationName,
                toReadableDayString(startDate),
                toReadableDayString(endDate));
    }
}
