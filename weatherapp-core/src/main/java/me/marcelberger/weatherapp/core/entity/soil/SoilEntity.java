package me.marcelberger.weatherapp.core.entity.soil;

import jakarta.persistence.*;
import lombok.*;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "so_soil")
public class SoilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
    private StationEntity station;
}
