package me.marcelberger.weatherapp.api.dto.response.data.month;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.api.dto.response.data.WeatherSummaryDataResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WeatherMonthDataResponseDto extends WeatherSummaryDataResponseDto {
    private String year;
    private String month;
}
