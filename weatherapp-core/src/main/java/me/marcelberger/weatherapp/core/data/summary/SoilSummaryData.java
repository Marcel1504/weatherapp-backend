package me.marcelberger.weatherapp.core.data.summary;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class SoilSummaryData {
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
