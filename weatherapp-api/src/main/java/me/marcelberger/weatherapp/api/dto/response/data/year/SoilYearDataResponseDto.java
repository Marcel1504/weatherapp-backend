package me.marcelberger.weatherapp.api.dto.response.data.year;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SoilYearDataResponseDto implements DataResponseDto {
    private Integer amount;
    private Double temperature50cmMax;
    private Double temperature50cmMin;
    private Double temperature50cmAvg;
    private Double temperature100cmMax;
    private Double temperature100cmMin;
    private Double temperature100cmAvg;
    private Double temperature200cmMax;
    private Double temperature200cmMin;
    private Double temperature200cmAvg;
    private String year;
}
