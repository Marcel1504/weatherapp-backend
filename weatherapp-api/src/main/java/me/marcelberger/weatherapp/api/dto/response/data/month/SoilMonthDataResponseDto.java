package me.marcelberger.weatherapp.api.dto.response.data.month;

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
public class SoilMonthDataResponseDto extends SoilSummaryDataResponseDto {
    private String year;
    private String month;
}
