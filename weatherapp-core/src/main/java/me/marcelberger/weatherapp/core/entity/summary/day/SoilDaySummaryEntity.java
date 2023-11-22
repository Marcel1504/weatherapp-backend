package me.marcelberger.weatherapp.core.entity.summary.day;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.summary.SoilSummaryEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "so_soil_day")
public class SoilDaySummaryEntity extends SoilSummaryEntity {

    @Column(name = "day")
    private String day;
}
