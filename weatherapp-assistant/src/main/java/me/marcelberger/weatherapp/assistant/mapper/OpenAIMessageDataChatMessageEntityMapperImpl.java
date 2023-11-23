package me.marcelberger.weatherapp.assistant.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIRoleEnum;
import me.marcelberger.weatherapp.assistant.exception.AssistantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenAIMessageDataChatMessageEntityMapperImpl implements Mapper<OpenAIMessageData, ChatMessageEntity> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ChatMessageEntity map(OpenAIMessageData object) {
        try {
            return ChatMessageEntity.builder()
                    .role(ChatRoleEnum.valueOf(object.getRole().getValue().toUpperCase()))
                    .type(getType(object))
                    .content(object.getContent())
                    .openAIMessage(objectMapper.writeValueAsString(object))
                    .build();
        } catch (JsonProcessingException e) {
            throw new AssistantException("Can not map OpenAI data to chat message");
        }
    }

    private ChatTypeEnum getType(OpenAIMessageData openAIMessage) {
        if (openAIMessage.getRole() == OpenAIRoleEnum.ASSISTANT && openAIMessage.getFunctionCall() != null) {
            return ChatTypeEnum.INTERNAL;
        }
        if (openAIMessage.getRole() == OpenAIRoleEnum.FUNCTION && openAIMessage.getName() != null) {
            return switch (openAIMessage.getName()) {
                case WEATHER_MEDIA -> ChatTypeEnum.WEATHER_MEDIA;
                case WEATHER_TIME, WEATHER_RECORD -> ChatTypeEnum.WEATHER_DATA;
            };
        }
        return ChatTypeEnum.TEXT;
    }
}
