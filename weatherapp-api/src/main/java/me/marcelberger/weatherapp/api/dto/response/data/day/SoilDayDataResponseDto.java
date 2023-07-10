package me.marcelberger.weatherapp.api.dto.response.data.day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.api.dto.response.data.SoilSummaryDataResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SoilDayDataResponseDto extends SoilSummaryDataResponseDto {
    private String day;
}
