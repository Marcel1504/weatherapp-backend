package me.marcelberger.weatherapp.assistant.controller;

import me.marcelberger.weatherapp.assistant.dto.request.chat.ChatRequestDto;
import me.marcelberger.weatherapp.assistant.dto.response.chat.ChatResponseDto;
import me.marcelberger.weatherapp.assistant.facade.ChatFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatFacade chatFacade;

    @PostMapping
    public ResponseEntity<ChatResponseDto> sendChatMessage(@RequestBody ChatRequestDto chatRequest) {
        return ResponseEntity.ok(chatFacade.sendChatMessage(chatRequest));
    }
}
