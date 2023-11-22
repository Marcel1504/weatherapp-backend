package me.marcelberger.weatherapp.assistant.service.openai.api;

import me.marcelberger.weatherapp.assistant.dto.request.openai.OpenAIRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.openai.OpenAIResponseDto;

public interface OpenAIApiService {
    OpenAIResponseDto sendRequest(OpenAIRequestDto request);
}

