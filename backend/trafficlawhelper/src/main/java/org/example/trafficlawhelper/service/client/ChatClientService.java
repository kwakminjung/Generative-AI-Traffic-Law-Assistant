package org.example.trafficlawhelper.service.client;

import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.dto.response.QuizResponse;
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

    public String generate(String userMessage) {
        return this.chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public List<QuizResponse> quiz() {
        BeanOutputConverter<List<QuizResponse>> outputConverter = new BeanOutputConverter<>(
                new ParameterizedTypeReference<>() {});

        String format = outputConverter.getFormat();
        String template = """
                Create the quiz list.
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

        return outputConverter.convert(generation.getOutput().getContent());
    }
}