package edu.java.clients;

import edu.java.requests.requestsToBot.LinkUpdateRequest;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BotClient {
    private final WebClient webClient;

    public BotClient(WebClient wc) {
        this.webClient = wc;
    }

    public Mono<String> getUpdates(LinkUpdateRequest request) {
        return webClient
                .post()
                .uri("/updates")
                .body((BodyInserter<?, ? super ClientHttpRequest>) request)
                .retrieve()
                .bodyToMono(String.class);
    }
}
