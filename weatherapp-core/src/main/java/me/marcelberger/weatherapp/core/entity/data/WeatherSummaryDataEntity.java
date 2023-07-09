package me.marcelberger.weatherapp.core.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.enumeration.WeatherWindDirectionEnum;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WeatherSummaryDataEntity extends DataEntity {
    @Column(name = "amount")
    protected Integer amount;

    @Column(name = "temperature_avg")
    protected Double temperatureAvg;

    @Column(name = "temperature_min")
    protected Double temperatureMin;

    @Column(name = "temperature_max")
    protected Double temperatureMax;

    @Column(name = "humidity_avg")
    protected Double humidityAvg;

    @Column(name = "humidity_min")
    protected Integer humidityMin;

    @Column(name = "humidity_max")
    protected Integer humidityMax;

    @Column(name = "rain_total")
    protected Double rainTotal;

    @Column(name = "rain_rate_max")
    protected Double rainRateMax;

    @Column(name = "wind_max")
    protected Double windMax;

    @Column(name = "wind_direction_cluster")
    @Enumerated(EnumType.STRING)
    private WeatherWindDirectionEnum windDirectionCluster;

    @Column(name = "pressure_avg")
    protected Double pressureAvg;

    @Column(name = "pressure_min")
    protected Double pressureMin;

    @Column(name = "pressure_max")
    protected Double pressureMax;

    @Column(name = "solar_radiation_avg")
    protected Double solarRadiationAvg;

    @Column(name = "solar_radiation_min")
    protected Double solarRadiationMin;

    @Column(name = "solar_radiation_max")
    protected Double solarRadiationMax;
}
