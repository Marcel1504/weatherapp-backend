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
import me.marcelberger.weatherapp.assistant.error.AssistantError;
import me.marcelberger.weatherapp.assistant.service.chat.ChatService;
import me.marcelberger.weatherapp.assistant.service.openai.function.OpenAIFunctionService;
import me.marcelberger.weatherapp.assistant.service.openai.property.OpenAIPropertyService;
import me.marcelberger.weatherapp.assistant.service.openai.sender.OpenAISenderService;
import me.marcelberger.weatherapp.core.data.station.StationData;
import me.marcelberger.weatherapp.core.mapper.Mapper;

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

    private final List<ChatMessageEntity> chatResponseMessages = new ArrayList<>();

    public List<ChatMessageEntity> execute() {
        validateInputs();
        ChatEntity updatedChat = chat;

        // prepare OpenAI messages with data from system, existing chat messages and the new user message
        openAIMessages.add(openAIPropertyService.generateSystemMessage());
        openAIMessages.addAll(chatMessageEntityOpenAIMessageDataMapper.mapToPage(chat.getMessages()).getList());
        updatedChat = addUserMessage(updatedChat);

        // send initial request to OpenAI
        OpenAIResponseDto response = openAISenderService.sendChat(openAIMessages, contextStation);
        validateOpenAIResponse(response);

        // update from OpenAI response
        OpenAIChoiceData firstChoice = response.getChoices().get(0);
        openAIMessages.add(firstChoice.getMessage());
        chatResponseMessages.add(openAIMessageDataChatMessageEntityMapper.map(firstChoice.getMessage()));
        updatedChat = chatService.updateTokensForChat(updatedChat, Long.valueOf(response.getUsage().getTotalToken()));

        // check if a function call happened and handle it
        if (firstChoice.getFinishReason().equals(OpenAIFinishReasonEnum.FUNCTION_CALL)) {
            updatedChat = handleFunctionCall(updatedChat, firstChoice.getMessage().getFunctionCall());
        }

        // save all new chat messages
        for (ChatMessageEntity chatMessage : chatResponseMessages) {
            updatedChat = chatService.saveChatMessageToChatById(updatedChat, chatMessage);
        }
        return chatResponseMessages;
    }

    private void validateInputs() {
        if (userMessage == null || userMessage.isBlank()
                || chat == null || chat.getMessages() == null
                || contextStation == null || contextStation.getName() == null) {
            throw new AssistantError(
                    AssistantError.Code.ASSISTANT00002,
                    "Could not execute OpenAI chat: Validation failed");
        }
    }

    private void validateOpenAIResponse(OpenAIResponseDto response) {
        if (response == null
                || response.getChoices() == null
                || response.getChoices().get(0) == null
                || response.getUsage() == null) {
            throw new AssistantError(
                    AssistantError.Code.ASSISTANT00002,
                    "Response from OpenAI is invalid");
        }
    }

    private ChatEntity addUserMessage(ChatEntity chat) {
        OpenAIMessageData openAIUserMessage = openAIPropertyService.generateUserMessage(userMessage);
        openAIMessages.add(openAIUserMessage);
        return chatService.saveChatMessageToChatById(
                chat,
                openAIMessageDataChatMessageEntityMapper.map(openAIUserMessage));
    }

    private ChatEntity handleFunctionCall(ChatEntity chat, OpenAIFunctionCallData functionCall) {
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
        validateOpenAIResponse(response);

        // update from OpenAI response
        OpenAIChoiceData firstChoice = response.getChoices().get(0);
        openAIMessages.add(firstChoice.getMessage());
        chatResponseMessages.add(openAIMessageDataChatMessageEntityMapper.map(firstChoice.getMessage()));
        return chatService.updateTokensForChat(chat, Long.valueOf(response.getUsage().getTotalToken()));
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
        chatResponseMessages.add(functionResultChatMessageEntity);
    }
}
