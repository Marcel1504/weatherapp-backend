package me.marcelberger.weatherapp.core.data.summary.hour;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.data.summary.WeatherSummaryData;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WeatherHourSummaryData extends WeatherSummaryData {
    private String day;
    private String hour;
}
