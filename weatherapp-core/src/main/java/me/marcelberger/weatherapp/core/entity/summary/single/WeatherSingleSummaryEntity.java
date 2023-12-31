package me.marcelberger.weatherapp.core.entity.summary.single;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "we_weather")
public class WeatherSingleSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "station_id")
    protected StationEntity station;
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
}