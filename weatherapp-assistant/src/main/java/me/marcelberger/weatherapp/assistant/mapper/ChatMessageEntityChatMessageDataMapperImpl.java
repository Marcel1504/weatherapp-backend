package me.marcelberger.weatherapp.assistant.mapper;

import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageEntityChatMessageDataMapperImpl implements Mapper<ChatMessageEntity, ChatMessageData> {
    @Override
    public ChatMessageData map(ChatMessageEntity object) {
        return ChatMessageData.builder()
                .timestamp(object.getTimestamp())
                .role(object.getRole())
                .content(object.getContent())
                .type(object.getType())
                .build();
    }
}
