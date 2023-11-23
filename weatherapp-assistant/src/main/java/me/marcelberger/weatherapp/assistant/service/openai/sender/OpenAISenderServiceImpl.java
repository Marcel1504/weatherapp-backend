package me.marcelberger.weatherapp.assistant.service.openai.sender;

import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.dto.request.openai.OpenAIRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.openai.OpenAIResponseDto;
import me.marcelberger.weatherapp.assistant.service.openai.api.OpenAIApiService;
import me.marcelberger.weatherapp.assistant.service.openai.property.OpenAIPropertyService;
import me.marcelberger.weatherapp.core.data.station.StationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAISenderServiceImpl implements OpenAISenderService {

    @Autowired
    private OpenAIPropertyService openAIPropertyService;

    @Autowired
    private OpenAIApiService openAIApiService;

    @Value("${weatherapp.openAI.model}")
    private String openAIModel;

    @Override
    public OpenAIResponseDto sendChat(List<OpenAIMessageData> messages, StationData contextStation) {
        OpenAIRequestDto chatRequest = OpenAIRequestDto.builder()
                .model(openAIModel)
                .messages(messages)
                .functions(openAIPropertyService.getAvailableFunctions(contextStation))
                .build();
        return openAIApiService.sendRequest(chatRequest);
    }
}
