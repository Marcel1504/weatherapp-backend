package me.marcelberger.weatherapp.assistant.service.openai.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionCallData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.error.AssistantError;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OpenAIFunctionService<DATA> {

    @Autowired
    private ObjectMapper objectMapper;

    public OpenAIFunctionResultData executeFunctionCall(OpenAIFunctionCallData functionCall) {
        try {
            DATA data = objectMapper.readValue(functionCall.getArguments(), getFunctionCallDataClass());
            return executeByFunctionCalData(data);
        } catch (JsonProcessingException exception) {
            throw new AssistantError(
                    AssistantError.Code.ASSISTANT00002,
                    "Can not process function call data provided by OpenAI");
        }
    }

    protected OpenAIFunctionResultData buildNoResultData() {
        return OpenAIFunctionResultData.builder()
                .resultShort("No result")
                .resultLong(null)
                .build();
    }

    protected String writeDataAsString(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new AssistantError(AssistantError.Code.ASSISTANT00002, "Can not process data");
        }
    }

    public abstract OpenAIFunctionEnum getFunction();

    protected abstract Class<DATA> getFunctionCallDataClass();

    protected abstract OpenAIFunctionResultData executeByFunctionCalData(DATA data);
}
