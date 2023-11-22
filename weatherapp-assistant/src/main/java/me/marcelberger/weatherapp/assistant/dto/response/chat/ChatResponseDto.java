package me.marcelberger.weatherapp.assistant.dto.response.chat;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;

import java.util.List;

@Data
@Builder
public class ChatResponseDto {
    private Long chatId;
    private List<ChatMessageData> messages;
}