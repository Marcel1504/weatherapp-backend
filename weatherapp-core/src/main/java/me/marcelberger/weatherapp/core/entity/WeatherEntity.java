package me.marcelberger.weatherapp.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "we_weather")
public class WeatherEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "rain_delta")
    private Double rainDelta;

    @Column(name = "rain_rate")
    private Double rainRate;

    @Column(name = "wind")
    private Double wind;

    @Column(name = "wind_direction")
    private Integer windDirection;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "solar_radiation")
    private Double solarRadiation;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private StationEntity station;
}