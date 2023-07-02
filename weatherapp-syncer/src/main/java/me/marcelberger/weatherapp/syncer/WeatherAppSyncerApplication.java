package me.marcelberger.weatherapp.syncer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "me.marcelberger.weatherapp")
@EntityScan("me.marcelberger.weatherapp")
@EnableJpaRepositories("me.marcelberger.weatherapp")
public class WeatherAppSyncerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppSyncerApplication.class, args);
    }
}