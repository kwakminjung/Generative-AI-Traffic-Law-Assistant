package org.example.trafficlawhelper.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class ClientController {

    private final ChatClient chatClient;

    @GetMapping
    public String chat(@RequestParam(defaultValue = "안녕하세요.") String userMessage) {
        return this.chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
