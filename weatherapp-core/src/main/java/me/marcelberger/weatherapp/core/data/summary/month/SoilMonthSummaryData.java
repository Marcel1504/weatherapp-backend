package me.marcelberger.weatherapp.core.data.summary.month;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.SoilSummaryData;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SoilMonthSummaryData extends SoilSummaryData {
    private String year;
    private String month;
}
