package org.example.trafficlawhelper.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatClientService {

    private final ChatClient chatClient;

    public String generate(String userMessage) {
        return this.chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
