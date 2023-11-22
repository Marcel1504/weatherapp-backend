package me.marcelberger.weatherapp.core.data.summary.month;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.WeatherSummaryData;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WeatherMonthSummaryData extends WeatherSummaryData {
    private String year;
    private String month;
}
