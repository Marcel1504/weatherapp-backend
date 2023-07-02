package me.marcelberger.weatherapp.core.entity.soil.summary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "so_soil_month")
public class SoilMonthEntity extends SoilSummaryEntity {

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;
}
