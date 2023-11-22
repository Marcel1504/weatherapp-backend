package me.marcelberger.weatherapp.core.entity.summary.hour;

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
@Table(name = "we_weather_hour")
public class WeatherHourSummaryEntity extends WeatherSummaryEntity {

    @Column(name = "hour")
    private String hour;

    @Column(name = "day")
    private String day;
}
