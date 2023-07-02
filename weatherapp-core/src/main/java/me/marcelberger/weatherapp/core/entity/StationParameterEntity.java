package me.marcelberger.weatherapp.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cn_station_parameter")
public class StationParameterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private StationEntity station;
}
