package me.marcelberger.weatherapp.assistant.service.openai.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionCallData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.assistant.exception.AssistantException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OpenAIFunctionService<DATA> {

    @Autowired
    private ObjectMapper objectMapper;

    public OpenAIFunctionResultData executeFunctionCall(OpenAIFunctionCallData functionCall) {
        try {
            DATA data = objectMapper.readValue(functionCall.getArguments(), getFunctionCallDataClass());
            return executeByFunctionCalData(data);
        } catch (JsonProcessingException exception) {
            throw new AssistantException("Can not map OpenAI function call arguments");
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
            throw new AssistantException("Can not process data");
        }
    }

    public abstract OpenAIFunctionEnum getFunction();

    protected abstract Class<DATA> getFunctionCallDataClass();

    protected abstract OpenAIFunctionResultData executeByFunctionCalData(DATA data);
}
