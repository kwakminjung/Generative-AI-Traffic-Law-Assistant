package org.example.trafficlawhelper.controller.client;

import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.service.client.ChatClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class ClientController {

    private final ChatClientService chatClientService;

    @GetMapping
    public Map<String, String> chat(@RequestParam String userMessage) {
        String response = chatClientService.generate(userMessage);
        return Map.of("response", response);
    }
}
