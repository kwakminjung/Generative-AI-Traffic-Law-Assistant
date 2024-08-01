package org.example.trafficlawhelper.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.dto.request.UserTextRequest;
import org.example.trafficlawhelper.dto.response.QuizResponse;
import org.example.trafficlawhelper.dto.response.UserTextResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatClientService {

    private final ChatClient chatClient;

    public UserTextResponse chat(UserTextRequest request) {
        return new UserTextResponse(this.chatClient.prompt()
                .user(request.request())
                .call()
                .content());
    }

    public List<QuizResponse> quiz() {
        BeanOutputConverter<List<QuizResponse>> outputConverter = new BeanOutputConverter<>(
                new ParameterizedTypeReference<>() {
                });

        String format = outputConverter.getFormat();
        String template = """
                Create the quiz list based on stored data in Korean.
                There are 10 quizzes on the quiz list.
                A quiz consists of questions, 4 options, and correct answers, and only one of the options exists.
                For options, present items that are similar to the actual answers.
                {format}
                """;

        Prompt prompt = new Prompt(new PromptTemplate(template, Map.of("format", format)).createMessage());

        Generation generation = this.chatClient.prompt(prompt)
                .call()
                .chatResponse()
                .getResult();

        String content = generation.getOutput().getContent();

        ObjectMapper objectMapper = new ObjectMapper();

        String result;
        try {
            result = objectMapper.readTree(content).get("items").toString();
            return objectMapper.readValue(result, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}