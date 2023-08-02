package me.marcelberger.weatherapp.api.service.export.csv.impl;

import me.marcelberger.weatherapp.api.service.export.csv.ExportCsvService;
import me.marcelberger.weatherapp.core.entity.data.day.SoilDayDataEntity;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SoilExportCsvServiceImpl implements ExportCsvService<SoilDayDataEntity> {
    @Override
    public void printRecord(CSVPrinter printer, SoilDayDataEntity item) throws IOException {
        printer.printRecord(
                item.getDay(),
                item.getAmount(),
                item.getTemperature50cmAvg(),
                item.getTemperature50cmMax(),
                item.getTemperature50cmMin(),
                item.getTemperature100cmAvg(),
                item.getTemperature100cmMax(),
                item.getTemperature100cmMin(),
                item.getTemperature200cmAvg(),
                item.getTemperature200cmMax(),
                item.getTemperature200cmMin()
        );
    }

    @Override
    public String[] headers() {
        return new String[]{
                "day",
                "amount",
                "temp50cmAvg",
                "temp50cmMax",
                "temp50cmMin",
                "temp100cmAvg",
                "temp100cmMax",
                "temp100cmMin",
                "temp200cmAvg",
                "temp200cmMax",
                "temp200cmMin"
        };
    }
}
