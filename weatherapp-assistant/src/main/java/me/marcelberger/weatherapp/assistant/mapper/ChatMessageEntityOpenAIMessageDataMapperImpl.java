package me.marcelberger.weatherapp.assistant.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.core.exception.CoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageEntityOpenAIMessageDataMapperImpl implements Mapper<ChatMessageEntity, OpenAIMessageData> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public OpenAIMessageData map(ChatMessageEntity object) {
        try {
            return objectMapper.readValue(object.getOpenAIMessage(), OpenAIMessageData.class);
        } catch (JsonProcessingException e) {
            throw new CoreException("Can not map chat message to openAI message: %s", e.getMessage());
        }
    }
}
