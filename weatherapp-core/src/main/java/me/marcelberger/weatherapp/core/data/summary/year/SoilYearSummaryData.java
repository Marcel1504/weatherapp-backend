package me.marcelberger.weatherapp.core.data.summary.year;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.SoilSummaryData;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SoilYearSummaryData extends SoilSummaryData {
    private String year;
}
