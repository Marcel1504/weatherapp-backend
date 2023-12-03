package me.marcelberger.weatherapp.assistant.error;

import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import java.net.SocketTimeoutException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class AssistantErrorHandler {

    /**
     * Handle AssistantError
     *
     * @param e AssistantError
     * @return ChatResponseDto
     */
    @ExceptionHandler(AssistantError.class)
    public ResponseEntity<ChatResponseDto> handleAssistantError(AssistantError e) {
        log.warn("AssistantError(code={}): {}", e.getCode().getValue(), e.getMessage());
        ChatMessageData messageData = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content(e.getCode().getValue())
                .build();
        return ResponseEntity.ok(ChatResponseDto.builder()
                .messages(List.of(messageData))
                .build());
    }

    /**
     * Handle timeouts from OpenAI-API
     *
     * @param e SocketTimeoutException
     * @return ChatResponseDto
     */
    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<ChatResponseDto> handleSocketTimeoutException(SocketTimeoutException e) {
        log.warn("AssistantError(code={}): {}", AssistantError.Code.ASSISTANT00001.getValue(), e.getMessage());
        ChatMessageData messageData = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content(AssistantError.Code.ASSISTANT00001.getValue())
                .build();
        return ResponseEntity.ok(ChatResponseDto.builder()
                .messages(List.of(messageData))
                .build());
    }

    /**
     * Handle HTTP error responses from OpenAI-API
     *
     * @param e HttpStatusCodeException
     * @return ChatResponseDto
     */
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ChatResponseDto> handleHttpStatusCodeException(HttpStatusCodeException e) {
        log.warn("AssistantError(code={}): {}", AssistantError.Code.ASSISTANT00003.getValue(), e.getMessage());
        ChatMessageData messageData = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content(AssistantError.Code.ASSISTANT00003.getValue())
                .build();
        return ResponseEntity.ok(ChatResponseDto.builder()
                .messages(List.of(messageData))
                .build());
    }
}
