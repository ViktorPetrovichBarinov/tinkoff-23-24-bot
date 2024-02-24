package edu.java.clients;

import edu.java.responses.stackoverflow.StackOverflowQuestionsResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StackOverflowClient {
    private final WebClient webClient;

    public StackOverflowClient(WebClient wc) {
        this.webClient = wc;
    }

    public Mono<StackOverflowQuestionsResponse> fetchQuestion(long questionId) {
        return webClient
                .get()
                .uri("/questions/{id}?site=stackoverflow", questionId)
                .retrieve()
                .bodyToMono(StackOverflowQuestionsResponse.class);

    }

}
