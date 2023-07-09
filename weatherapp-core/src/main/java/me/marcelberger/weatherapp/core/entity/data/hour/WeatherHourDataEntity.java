package me.marcelberger.weatherapp.core.entity.data.hour;

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
@Table(name = "we_weather_hour")
public class WeatherHourDataEntity extends WeatherSummaryDataEntity {

    @Column(name = "hour")
    private String hour;

    @Column(name = "day")
    private String day;
}
