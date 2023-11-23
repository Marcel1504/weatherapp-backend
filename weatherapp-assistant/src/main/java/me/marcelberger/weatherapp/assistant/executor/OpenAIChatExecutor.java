package me.marcelberger.weatherapp.assistant.executor;

import lombok.Builder;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIChoiceData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionCallData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.dto.response.openai.OpenAIResponseDto;
import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFinishReasonEnum;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIRoleEnum;
import me.marcelberger.weatherapp.assistant.exception.AssistantException;
import me.marcelberger.weatherapp.assistant.mapper.Mapper;
import me.marcelberger.weatherapp.assistant.service.chat.ChatService;
import me.marcelberger.weatherapp.assistant.service.openai.function.OpenAIFunctionService;
import me.marcelberger.weatherapp.assistant.service.openai.property.OpenAIPropertyService;
import me.marcelberger.weatherapp.assistant.service.openai.sender.OpenAISenderService;
import me.marcelberger.weatherapp.core.data.station.StationData;

import java.util.ArrayList;
import java.util.List;

@Builder
public class OpenAIChatExecutor {

    private final ChatService chatService;

    private final String userMessage;

    private final StationData contextStation;

    private final OpenAISenderService openAISenderService;

    private final OpenAIPropertyService openAIPropertyService;

    private final List<OpenAIFunctionService<?>> openAIFunctionServices;

    private final Mapper<ChatMessageEntity, OpenAIMessageData> chatMessageEntityOpenAIMessageDataMapper;

    private final Mapper<OpenAIMessageData, ChatMessageEntity> openAIMessageDataChatMessageEntityMapper;

    private final ChatEntity chat;

    private final List<OpenAIMessageData> openAIMessages = new ArrayList<>();

    private final List<ChatMessageEntity> newChatMessages = new ArrayList<>();

    public List<ChatMessageEntity> execute() {
        validate();
        openAIMessages.add(openAIPropertyService.generateSystemMessage());
        openAIMessages.addAll(chatMessageEntityOpenAIMessageDataMapper.map(chat.getMessages()));
        addUserMessage();

        // send first request to OpenAI with the user message
        OpenAIResponseDto response = openAISenderService.sendChat(openAIMessages, contextStation);
        OpenAIChoiceData firstChoice = response.getChoices().get(0);
        openAIMessages.add(firstChoice.getMessage());
        newChatMessages.add(openAIMessageDataChatMessageEntityMapper.map(firstChoice.getMessage()));

        if (firstChoice.getFinishReason().equals(OpenAIFinishReasonEnum.FUNCTION_CALL)) {
            // function call happened
            handleFunctionCall(firstChoice.getMessage().getFunctionCall());
        }

        // save all new chat messages
        ChatEntity newChat = chat;
        for (ChatMessageEntity chatMessage : newChatMessages) {
            newChat = chatService.saveChatMessageToChatById(newChat, chatMessage);
        }
        return newChatMessages;
    }

    private void validate() {
        if (userMessage == null || userMessage.isBlank()
                || chat == null || chat.getMessages() == null
                || contextStation == null || contextStation.getName() == null) {
            throw new AssistantException("Could not execute OpenAI chat: Validation failed");
        }
    }

    private void addUserMessage() {
        OpenAIMessageData openAIUserMessage = openAIPropertyService.generateUserMessage(userMessage);
        openAIMessages.add(openAIUserMessage);
        newChatMessages.add(openAIMessageDataChatMessageEntityMapper.map(openAIUserMessage));
    }

    private void handleFunctionCall(OpenAIFunctionCallData functionCall) {
        // perform actual function call and get result
        OpenAIFunctionResultData functionResult = null;
        OpenAIFunctionService<?> functionService = openAIFunctionServices.stream()
                .filter(s -> s.getFunction().equals(functionCall.getName()))
                .findFirst()
                .orElse(null);
        if (functionService != null) {
            functionResult = functionService.executeFunctionCall(functionCall);
        }

        // handle function result messages
        handleFunctionCallResult(functionCall.getName(), functionResult);

        // perform openAI chat with function result
        OpenAIResponseDto response = openAISenderService.sendChat(openAIMessages, contextStation);
        OpenAIChoiceData firstChoice = response.getChoices().get(0);
        openAIMessages.add(firstChoice.getMessage());
        newChatMessages.add(openAIMessageDataChatMessageEntityMapper.map(firstChoice.getMessage()));
    }

    private void handleFunctionCallResult(OpenAIFunctionEnum function, OpenAIFunctionResultData functionResult) {
        OpenAIMessageData functionResultOpenAIMessage = OpenAIMessageData.builder()
                .role(OpenAIRoleEnum.FUNCTION)
                .name(function)
                .content(functionResult != null && functionResult.getResultShort() != null
                        ? functionResult.getResultShort()
                        : "No result")
                .build();
        openAIMessages.add(functionResultOpenAIMessage);
        ChatMessageEntity functionResultChatMessageEntity =
                openAIMessageDataChatMessageEntityMapper.map(functionResultOpenAIMessage);
        functionResultChatMessageEntity.setContent(functionResult != null
                ? functionResult.getResultLong()
                : null);
        newChatMessages.add(functionResultChatMessageEntity);
    }
}
