package me.marcelberger.weatherapp.assistant.dto.request.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRequestDto {
    private Long chatId;
    private String message;
    private String contextStationCode;
}
