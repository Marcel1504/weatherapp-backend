package me.marcelberger.weatherapp.core.entity.data.single;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "so_soil")
public class SoilDataEntity extends DataEntity {

    @Column(name = "temperature50cm")
    private Double temperature50cm;

    @Column(name = "temperature100cm")
    private Double temperature100cm;

    @Column(name = "temperature200cm")
    private Double temperature200cm;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
