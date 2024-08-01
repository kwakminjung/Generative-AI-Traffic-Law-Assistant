package org.example.trafficlawhelper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final VectorStore vectorStore;

    private final ChatMemory chatMemory;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        String PROMPT_STRING = """
                Answer in Korean.
                If there is no relevant information, reply as follows : 요청하신 질문에 대한 답변을 제공할 수 없습니다. 죄송합니다.
                     """;

        return chatClientBuilder.defaultSystem(PROMPT_STRING)
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .build();
    }
}
