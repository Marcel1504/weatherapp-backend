package me.marcelberger.weatherapp.core.entity.data.year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.marcelberger.weatherapp.core.entity.data.WeatherSummaryDataEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "we_weather_year")
public class WeatherYearDataEntity extends WeatherSummaryDataEntity {

    @Column(name = "year")
    private String year;
}
