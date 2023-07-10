package me.marcelberger.weatherapp.core.entity.data.single;

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
@Table(name = "so_soil")
public class SoilSingleDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "temperature50cm")
    private Double temperature50cm;

    @Column(name = "temperature100cm")
    private Double temperature100cm;

    @Column(name = "temperature200cm")
    private Double temperature200cm;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "station_id")
    protected StationEntity station;
}
