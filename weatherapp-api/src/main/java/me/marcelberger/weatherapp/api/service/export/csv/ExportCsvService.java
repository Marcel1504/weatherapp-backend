package me.marcelberger.weatherapp.api.service.export.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface ExportCsvService<SOURCE> {

    void printRecord(CSVPrinter printer, SOURCE item) throws IOException;

    String[] headers();

    default void generateCSVFile(List<SOURCE> list, String absoluteFilePath) throws IOException {
        FileWriter out = new FileWriter(absoluteFilePath);
        CSVPrinter printer = CSVFormat.Builder.create().setHeader(headers()).build().print(out);
        for (SOURCE item : list) {
            printRecord(printer, item);
        }
        printer.close();
        out.close();
    }
}
