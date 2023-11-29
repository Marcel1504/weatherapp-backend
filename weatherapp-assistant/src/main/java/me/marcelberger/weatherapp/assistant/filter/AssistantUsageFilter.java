package me.marcelberger.weatherapp.assistant.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import me.marcelberger.weatherapp.assistant.data.chat.ChatMessageData;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatRoleEnum;
import me.marcelberger.weatherapp.assistant.enumeration.chat.ChatTypeEnum;
import me.marcelberger.weatherapp.assistant.error.AssistantError;
import me.marcelberger.weatherapp.core.data.error.ErrorData;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;
import me.marcelberger.weatherapp.core.error.CoreError;
import me.marcelberger.weatherapp.core.filter.UsageFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class AssistantUsageFilter extends UsageFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${weatherapp.usage.requestsPerMinute}")
    private Long requestsPerMinute;

    @Override
    protected UsageModuleNameEnum getModuleName() {
        return UsageModuleNameEnum.ASSISTANT;
    }

    @Override
    protected Long getRequestsPerMinute() {
        return requestsPerMinute;
    }

    /**
     * Use custom error response handling to map errors to chat messages
     *
     * @param error    CoreError
     * @param response HttpServletResponse
     * @param status   HTTP-Status code
     */
    @Override
    protected void writeCoreErrorResponse(ErrorData<CoreError.Code> error,
                                          HttpServletResponse response,
                                          Integer status) throws IOException {
        ChatMessageData chatMessage = ChatMessageData.builder()
                .role(ChatRoleEnum.ASSISTANT)
                .type(ChatTypeEnum.ERROR)
                .content(status == 429
                        ? AssistantError.Code.ASSISTANT00004.getValue()
                        : AssistantError.Code.ASSISTANT00003.getValue())
                .build();
        ChatResponseDto chatResponse = ChatResponseDto.builder()
                .chatId(null)
                .messages(List.of(chatMessage))
                .build();
        response.getWriter().write(objectMapper.writeValueAsString(chatResponse));
        response.setStatus(200);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "application/json");
    }
}
