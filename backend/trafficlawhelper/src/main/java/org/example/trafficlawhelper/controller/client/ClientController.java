package org.example.trafficlawhelper.controller.client;

import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.dto.response.QuizResponse;
import org.example.trafficlawhelper.service.client.ChatClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class ClientController {

    private final ChatClientService chatClientService;

    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam String userMessage) {
        String response = chatClientService.generate(userMessage);
        return Map.of("response", response);
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<QuizResponse>> quiz() {
        List<QuizResponse> response = chatClientService.quiz();
        return (response != null) ?
            ResponseEntity.status(HttpStatus.OK).body(response) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
