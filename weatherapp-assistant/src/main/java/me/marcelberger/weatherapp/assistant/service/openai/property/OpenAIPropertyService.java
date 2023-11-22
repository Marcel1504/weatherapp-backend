package me.marcelberger.weatherapp.assistant.service.openai.property;

import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.core.entity.station.StationEntity;

import java.util.List;

public interface OpenAIPropertyService {
    List<OpenAIFunctionData> getAvailableFunctions(StationEntity contextStation);

    OpenAIMessageData generateSystemMessage();

    OpenAIMessageData generateUserMessage(String message);
}
