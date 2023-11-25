package me.marcelberger.weatherapp.assistant.facade;

import jakarta.transaction.Transactional;
import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.dto.request.chat.ChatRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;
import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.executor.OpenAIChatExecutor;
import me.marcelberger.weatherapp.assistant.service.chat.ChatService;
import me.marcelberger.weatherapp.assistant.service.openai.function.OpenAIFunctionService;
import me.marcelberger.weatherapp.assistant.service.openai.property.OpenAIPropertyService;
import me.marcelberger.weatherapp.assistant.service.openai.sender.OpenAISenderService;
import me.marcelberger.weatherapp.core.facade.station.StationFacade;
import me.marcelberger.weatherapp.core.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatFacadeImpl implements ChatFacade {

    @Autowired
    private StationFacade stationFacade;

    @Autowired
    private ChatService chatService;

    @Autowired
    private OpenAISenderService openAISenderService;

    @Autowired
    private OpenAIPropertyService openAIPropertyService;

    @Autowired
    private List<OpenAIFunctionService<?>> openAIFunctionServices;

    @Autowired
    private Mapper<ChatMessageEntity, ChatMessageData> chatMessageEntityChatMessageDataMapper;

    @Autowired
    private Mapper<ChatMessageEntity, OpenAIMessageData> chatMessageEntityOpenAIMessageDataMapper;

    @Autowired
    private Mapper<OpenAIMessageData, ChatMessageEntity> openAIMessageDataChatMessageEntityMapper;

    @Transactional
    @Override
    public ChatResponseDto sendChatMessage(ChatRequestDto chatRequest) {
        ChatEntity chat = chatRequest.getChatId() == null
                ? chatService.createChat()
                : chatService.getChatById(chatRequest.getChatId());
        List<ChatMessageEntity> chatMessageResults = OpenAIChatExecutor.builder()
                .userMessage(chatRequest.getMessage())
                .contextStation(stationFacade.getStationByCode(chatRequest.getContextStationCode()))
                .chat(chat)
                .chatService(chatService)
                .openAISenderService(openAISenderService)
                .openAIPropertyService(openAIPropertyService)
                .openAIFunctionServices(openAIFunctionServices)
                .chatMessageEntityOpenAIMessageDataMapper(chatMessageEntityOpenAIMessageDataMapper)
                .openAIMessageDataChatMessageEntityMapper(openAIMessageDataChatMessageEntityMapper)
                .build()
                .execute();
        return ChatResponseDto.builder()
                .chatId(chat.getId())
                .messages(mapChatMessageDataForResponse(chatMessageResults))
                .build();
    }

    private List<ChatMessageData> mapChatMessageDataForResponse(List<ChatMessageEntity> messages) {
        return chatMessageEntityChatMessageDataMapper.map(messages.stream()
                .filter(m -> m.getRole().equals(ChatRoleEnum.ASSISTANT) || m.getRole().equals(ChatRoleEnum.FUNCTION))
                .filter(m -> m.getContent() != null)
                .toList());
    }
}
