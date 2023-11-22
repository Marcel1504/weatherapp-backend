package me.marcelberger.weatherapp.assistant.facade;

import me.marcelberger.weatherapp.assistant.dto.request.chat.ChatRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;

public interface ChatFacade {
    ChatResponseDto sendChatMessage(ChatRequestDto chat);
}
