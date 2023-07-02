package me.marcelberger.weatherapp.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
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
    private Timestamp lastActivity;
}