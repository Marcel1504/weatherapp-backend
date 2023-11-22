package me.marcelberger.weatherapp.core.entity.summary.year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.summary.WeatherSummaryEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "we_weather_year")
public class WeatherYearSummaryEntity extends WeatherSummaryEntity {

    @Column(name = "year")
    private String year;
}
