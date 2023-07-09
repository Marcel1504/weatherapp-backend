package me.marcelberger.weatherapp.api.data.soil;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SoilSummaryData {
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
    private String day;
    private String month;
    private String year;
}
