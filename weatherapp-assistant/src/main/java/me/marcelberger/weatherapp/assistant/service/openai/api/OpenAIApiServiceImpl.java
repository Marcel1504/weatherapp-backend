package me.marcelberger.weatherapp.assistant.service.openai.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.dto.request.openai.OpenAIRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.openai.OpenAIResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIApiServiceImpl implements OpenAIApiService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("openAIApi")
    private RestTemplate restTemplate;

    @Value("${weatherapp.openAI.api.path}")
    private String openAIApiPath;

    @Override
    public OpenAIResponseDto sendRequest(OpenAIRequestDto request) {
        return restTemplate.postForObject(openAIApiPath, request, OpenAIResponseDto.class);
    }
}
