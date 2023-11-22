package me.marcelberger.weatherapp.core.data.summary.day;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.SoilSummaryData;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SoilDaySummaryData extends SoilSummaryData {
    private String day;
}
