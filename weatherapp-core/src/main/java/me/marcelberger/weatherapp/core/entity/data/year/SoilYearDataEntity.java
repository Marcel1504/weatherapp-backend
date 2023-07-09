package me.marcelberger.weatherapp.core.entity.data.year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.data.SoilSummaryDataEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "so_soil_year")
public class SoilYearDataEntity extends SoilSummaryDataEntity {

    @Column(name = "year")
    private String year;
}
