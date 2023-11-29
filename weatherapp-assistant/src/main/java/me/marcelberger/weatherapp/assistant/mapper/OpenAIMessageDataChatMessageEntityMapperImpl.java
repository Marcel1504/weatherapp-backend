package me.marcelberger.weatherapp.assistant.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIRoleEnum;
import me.marcelberger.weatherapp.assistant.error.AssistantError;
import me.marcelberger.weatherapp.assistant.service.openai.property.OpenAIPropertyService;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenAIMessageDataChatMessageEntityMapperImpl implements Mapper<OpenAIMessageData, ChatMessageEntity> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OpenAIPropertyService openAIPropertyService;

    @Override
    public ChatMessageEntity map(OpenAIMessageData object) {
        try {
            return ChatMessageEntity.builder()
                    .role(ChatRoleEnum.valueOf(object.getRole().getValue().toUpperCase()))
                    .type(getType(object))
                    .content(getCleanContent(object))
                    .openAIMessage(objectMapper.writeValueAsString(object))
                    .build();
        } catch (JsonProcessingException e) {
            throw new AssistantError(
                    AssistantError.Code.ASSISTANT00002,
                    "Can not map OpenAI message data to chat message");
        }
    }

    private ChatTypeEnum getType(OpenAIMessageData openAIMessage) {
        if (openAIMessage.getRole() == OpenAIRoleEnum.ASSISTANT && openAIMessage.getFunctionCall() != null) {
            return ChatTypeEnum.INTERNAL;
        }
        if (openAIMessage.getRole() == OpenAIRoleEnum.FUNCTION && openAIMessage.getName() != null) {
            return switch (openAIMessage.getName()) {
                case WEATHER_MEDIA -> ChatTypeEnum.WEATHER_MEDIA;
                case WEATHER_TIME -> ChatTypeEnum.WEATHER_TIME;
                case WEATHER_RECORD -> ChatTypeEnum.WEATHER_RECORD;
                case UNKNOWN -> ChatTypeEnum.ERROR;
            };
        }
        return ChatTypeEnum.TEXT;
    }

    private String getCleanContent(OpenAIMessageData object) {
        if (object.getRole() == OpenAIRoleEnum.USER) {
            return openAIPropertyService.cleanUserMessage(object.getContent());
        }
        return object.getContent();
    }
}
