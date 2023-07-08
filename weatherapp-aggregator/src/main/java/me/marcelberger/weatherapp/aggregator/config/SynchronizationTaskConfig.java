package me.marcelberger.weatherapp.aggregator.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
public class SynchronizationTaskConfig {

    @Bean
    @Qualifier("currentRunningSynchronizationTaskIds")
    public List<Long> currentRunningSynchronizationTaskIds() {
        return new ArrayList<>();
    }
}
