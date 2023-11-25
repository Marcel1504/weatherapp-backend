package me.marcelberger.weatherapp.assistant.service.openai.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIFunctionData;
import me.marcelberger.weatherapp.assistant.data.openai.OpenAIMessageData;
import me.marcelberger.weatherapp.assistant.enumeration.openai.OpenAIRoleEnum;
import me.marcelberger.weatherapp.core.data.station.StationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class OpenAIPropertyServiceImpl implements OpenAIPropertyService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${weatherapp.openAI.functions.available}")
    private Set<String> openAIFunctionsAvailable;

    @Value("${weatherapp.openAI.functions.filePattern}")
    private String openAIFunctionsFilePattern;

    @Value("${weatherapp.openAI.messageTemplate.system}")
    private String openAISystemMessageTemplate;

    @Value("${weatherapp.openAI.messageTemplate.user}")
    private String openAIUserMessageTemplate;

    @Override
    public List<OpenAIFunctionData> getAvailableFunctions(StationData contextStation) {

        List<OpenAIFunctionData> functions = new ArrayList<>();
        for (String name : openAIFunctionsAvailable) {
            try {
                String filePath = openAIFunctionsFilePattern.replace("{name}", name);
                String fileContent = Files.readString(Path.of(ResourceUtils.getFile(filePath).toURI()));
                fileContent = fileContent.replace("{station}", contextStation.getName());
                OpenAIFunctionData function = objectMapper.readValue(fileContent, OpenAIFunctionData.class);
                functions.add(function);
            } catch (IOException e) {
                log.warn(String.format("Could not load file for function %s: %s", name, e.getMessage()));
            }
        }
        return functions;
    }

    @Override
    public OpenAIMessageData generateSystemMessage() {
        String message = openAISystemMessageTemplate.replace(
                "{time}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return OpenAIMessageData.builder()
                .role(OpenAIRoleEnum.SYSTEM)
                .content(message)
                .build();
    }

    @Override
    public OpenAIMessageData generateUserMessage(String message) {
        return OpenAIMessageData.builder()
                .role(OpenAIRoleEnum.USER)
                .content(openAIUserMessageTemplate.replace("{message}", message))
                .build();
    }

    @Override
    public String cleanUserMessage(String message) {
        String textToRemove = openAIUserMessageTemplate.replace("{message}", "");
        return message.replace(textToRemove, "");
    }
}
