package me.marcelberger.weatherapp.assistant.data.openai;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenAIFunctionResultData {
    String resultShort;
    String resultLong;
}
