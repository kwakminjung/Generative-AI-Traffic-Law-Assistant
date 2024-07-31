package org.example.trafficlawhelper.controller.client;

import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.dto.request.UserTextRequest;
import org.example.trafficlawhelper.dto.response.QuizResponse;
import org.example.trafficlawhelper.dto.response.UserTextResponse;
import org.example.trafficlawhelper.service.client.ChatClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class ClientController {

    private final ChatClientService chatClientService;

    @PostMapping("/chat")
    public ResponseEntity<UserTextResponse> chat(@RequestBody UserTextRequest request) {
        UserTextResponse response = chatClientService.chat(request);
        return (response != null) ?
                ResponseEntity.status(HttpStatus.OK).body(response) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<QuizResponse>> quiz() {
        List<QuizResponse> response = chatClientService.quiz();
        return (response != null) ?
            ResponseEntity.status(HttpStatus.OK).body(response) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
