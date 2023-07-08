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
    private String temperature50cmMaxColor;
    private String temperature50cmMinColor;
    private String temperature50cmAvgColor;
    private Double temperature100cmMax;
    private Double temperature100cmMin;
    private Double temperature100cmAvg;
    private String temperature100cmMaxColor;
    private String temperature100cmMinColor;
    private String temperature100cmAvgColor;
    private Double temperature200cmMax;
    private Double temperature200cmMin;
    private Double temperature200cmAvg;
    private String temperature200cmMaxColor;
    private String temperature200cmMinColor;
    private String temperature200cmAvgColor;
    private String day;
    private String month;
    private String year;
}
