package me.marcelberger.weatherapp.assistant.service.chat;

import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;

public interface ChatService {
    ChatEntity createChat();

    ChatEntity getChatByIdOrNull(Long id);

    ChatEntity getChatById(Long id);

    void deleteChatById(Long id);

    ChatEntity saveChatMessageToChatById(ChatEntity chat, ChatMessageEntity chatMessage);
}
