package me.marcelberger.weatherapp.core.entity.station;

import jakarta.persistence.*;
import lombok.*;
import me.marcelberger.weatherapp.core.enumeration.station.StationTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cn_station")
public class StationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private StationTypeEnum type;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Integer altitude;

    @Column(name = "disabled")
    private Boolean disabled;

    @Column(name = "inactivity_threshold_hours")
    private Integer inactivityThresholdHours;

    @Column(name = "inactivity_issue_active")
    private Boolean inactivityIssueActive;

    @Column(name = "responsible_email")
    private String responsibleEmail;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;
}