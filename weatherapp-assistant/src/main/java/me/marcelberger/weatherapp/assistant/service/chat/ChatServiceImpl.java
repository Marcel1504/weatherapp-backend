package me.marcelberger.weatherapp.assistant.service.chat;

import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.exception.AssistantException;
import me.marcelberger.weatherapp.assistant.repository.ChatMessageRepository;
import me.marcelberger.weatherapp.assistant.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public ChatEntity createChat() {
        ChatEntity chat = ChatEntity.builder()
                .lastActivity(LocalDateTime.now())
                .totalTokensConsumed(0L)
                .maxTokensPerRequest(0L)
                .build();
        return chatRepository.save(chat);
    }

    @Override
    public ChatEntity getChatByIdOrNull(Long id) {
        ChatEntity chat = null;
        if (id != null) {
            chat = chatRepository.findById(id).orElse(null);
        }
        return chat;
    }

    @Override
    public ChatEntity getChatById(Long id) {
        ChatEntity chat = getChatByIdOrNull(id);
        if (chat == null) {
            throw new AssistantException("No chat found for the provided ID");
        }
        return chat;
    }

    @Override
    public void deleteChatById(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public ChatEntity saveChatMessageToChatById(ChatEntity chat, ChatMessageEntity chatMessage) {
        if (chat == null || chat.getId() == null) {
            throw new AssistantException("No chat provided to save chat messages");
        }
        if (chatMessage == null) {
            throw new AssistantException("No chat messages provided that can be saved");
        }
        chatMessage.setChat(chat);
        chat.getMessages().add(chatMessageRepository.save(chatMessage));
        return chat;
    }

    @Override
    public ChatEntity updateTokensForChat(ChatEntity chat, Long tokens) {
        if (chat == null || chat.getId() == null) {
            throw new AssistantException("No chat provided to update total tokens");
        }
        if (tokens == null) {
            tokens = 0L;
        }
        chat.setTotalTokensConsumed(chat.getTotalTokensConsumed() + tokens);
        chat.setMaxTokensPerRequest(chat.getMaxTokensPerRequest() < tokens
                ? tokens
                : chat.getMaxTokensPerRequest());
        return chatRepository.save(chat);
    }
}
