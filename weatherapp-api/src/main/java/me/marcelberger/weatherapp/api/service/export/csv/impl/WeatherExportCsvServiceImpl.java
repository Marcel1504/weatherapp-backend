package me.marcelberger.weatherapp.api.service.export.csv.impl;

import me.marcelberger.weatherapp.api.service.export.csv.ExportCsvService;
import me.marcelberger.weatherapp.core.entity.summary.day.WeatherDaySummaryEntity;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherExportCsvServiceImpl implements ExportCsvService<WeatherDaySummaryEntity> {
    @Override
    public void printRecord(CSVPrinter printer, WeatherDaySummaryEntity item) throws IOException {
        printer.printRecord(
                item.getDay(),
                item.getAmount(),
                item.getTemperatureAvg(),
                item.getTemperatureMax(),
                item.getTemperatureMin(),
                item.getHumidityAvg(),
                item.getHumidityMax(),
                item.getHumidityMin(),
                item.getRainTotal(),
                item.getRainRateMax(),
                item.getWindMax(),
                item.getWindDirectionCluster(),
                item.getPressureAvg(),
                item.getPressureMax(),
                item.getPressureMin(),
                item.getSolarRadiationAvg(),
                item.getSolarRadiationMax(),
                item.getSolarRadiationMin()
        );
    }

    @Override
    public String[] headers() {
        return new String[]{
                "day",
                "amount",
                "tempAvg",
                "tempMax",
                "tempMin",
                "humidityAvg",
                "humidityMax",
                "humidityMin",
                "rainTotal",
                "rainRateMax",
                "windMax",
                "windDirectionCluster",
                "pressureAvg",
                "pressureMax",
                "pressureMin",
                "solarRadiationAvg",
                "solarRadiationMax",
                "solarRadiationMin"
        };
    }
}
