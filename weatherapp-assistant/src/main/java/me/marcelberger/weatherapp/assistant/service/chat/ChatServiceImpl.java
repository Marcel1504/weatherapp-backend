package me.marcelberger.weatherapp.assistant.service.chat;

import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.repository.ChatMessageRepository;
import me.marcelberger.weatherapp.assistant.repository.ChatRepository;
import me.marcelberger.weatherapp.core.exception.CoreException;
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
            throw new CoreException("No chat with ID %s found", id);
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
            throw new CoreException("Can not save ChatMessageEntity: ChatEntity is null");
        }
        if (chatMessage == null) {
            throw new CoreException(
                    "Can not save ChatMessageEntity to ChatEntity(id=%s): ChatMessageEntity is null",
                    chat.getId());
        }
        chatMessage.setChat(chat);
        chat.getMessages().add(chatMessageRepository.save(chatMessage));
        return chat;
    }
}
