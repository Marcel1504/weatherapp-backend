package me.marcelberger.weatherapp.assistant.data.chat;

import lombok.Builder;
import lombok.Data;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatMessageData {
    private String content;
    private ChatRoleEnum role;
    private ChatTypeEnum type;
    private LocalDateTime timestamp;
}
