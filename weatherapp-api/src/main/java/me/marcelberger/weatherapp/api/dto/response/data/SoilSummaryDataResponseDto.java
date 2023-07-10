package me.marcelberger.weatherapp.api.dto.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class SoilSummaryDataResponseDto {
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
}
