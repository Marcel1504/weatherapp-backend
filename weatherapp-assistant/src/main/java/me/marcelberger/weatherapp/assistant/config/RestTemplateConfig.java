package me.marcelberger.weatherapp.assistant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Value("${weatherapp.openAI.api.host}")
    private String openAIApiHost;

    @Value("${weatherapp.openAI.api.key}")
    private String openAIApiKey;

    @Value("${weatherapp.openAI.api.timeoutSeconds}")
    private Long openAIApiTimeoutSeconds;

    @Bean(name = "openAIApi")
    public RestTemplate openAIApiRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri(openAIApiHost)
                .defaultHeader("Authorization", String.format("Bearer %s", openAIApiKey))
                .defaultHeader("Content-Type", "application/json")
                .setConnectTimeout(Duration.ofSeconds(openAIApiTimeoutSeconds))
                .setReadTimeout(Duration.ofSeconds(openAIApiTimeoutSeconds))
                .build();
    }
}
