package me.marcelberger.weatherapp.core.data.summary.day;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.WeatherSummaryData;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WeatherDaySummaryData extends WeatherSummaryData {
    private String day;
}
