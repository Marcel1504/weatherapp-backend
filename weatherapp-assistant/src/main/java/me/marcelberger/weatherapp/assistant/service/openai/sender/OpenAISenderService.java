package me.marcelberger.weatherapp.assistant.service.openai.sender;

import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.dto.response.openai.OpenAIResponseDto;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

import java.util.List;

public interface OpenAISenderService {
    OpenAIResponseDto sendChat(List<OpenAIMessageData> messages, StationEntity contextStation);
}
