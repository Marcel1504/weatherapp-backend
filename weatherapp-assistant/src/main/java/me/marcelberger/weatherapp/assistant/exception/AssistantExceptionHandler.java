package me.marcelberger.weatherapp.assistant.exception;

import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.SocketTimeoutException;
import java.util.List;

@ControllerAdvice
public class AssistantExceptionHandler {

    @ExceptionHandler(AssistantException.class)
    public ResponseEntity<ChatResponseDto> handleAssistantException(AssistantException e) {
        ChatMessageData messageData = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content(e.getMessage())
                .build();
        return ResponseEntity.ok(ChatResponseDto.builder()
                .messages(List.of(messageData))
                .build());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<ChatResponseDto> handleSocketTimeoutException(SocketTimeoutException e) {
        ChatMessageData messageData = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content("OpenAI-API timeout")
                .build();
        return ResponseEntity.ok(ChatResponseDto.builder()
                .messages(List.of(messageData))
                .build());
    }
}
