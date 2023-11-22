package me.marcelberger.weatherapp.assistant.service.openai.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionCallData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionResultData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIFunctionEnum;
import me.marcelberger.weatherapp.core.exception.CoreException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OpenAIFunctionService<DATA> {

    @Autowired
    private ObjectMapper objectMapper;

    public OpenAIFunctionResultData executeFunctionCall(OpenAIFunctionCallData functionCall) {
        try {
            DATA data = objectMapper.readValue(functionCall.getArguments(), getFunctionCallDataClass());
            return executeByFunctionCalData(data);
        } catch (JsonProcessingException exception) {
            throw new CoreException("Can not execute function call: %s", exception.getMessage());
        }
    }

    public abstract OpenAIFunctionEnum getFunction();

    protected abstract Class<DATA> getFunctionCallDataClass();

    protected abstract OpenAIFunctionResultData executeByFunctionCalData(DATA data);
}
