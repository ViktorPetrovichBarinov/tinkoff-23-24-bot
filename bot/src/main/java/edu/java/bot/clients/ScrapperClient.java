package edu.java.bot.clients;

import edu.java.bot.requests.requestsToScrapper.AddLinkRequest;
import edu.java.bot.requests.requestsToScrapper.RemoveLinkRequest;
import edu.java.bot.response.scrapper.LinkResponse;
import edu.java.bot.response.scrapper.ListLinkResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SuppressWarnings("MultipleStringLiterals")
public class ScrapperClient {
    private final WebClient webClient;
    private final static String START_PATH = "/scrapper";

    public ScrapperClient(WebClient wc) {
        this.webClient = wc;
    }

    public Mono<String> registerChat(Long id) {
        return webClient
                .post()
                .uri(START_PATH + "/tg-chat/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteChat(Long id) {
        return webClient
                .delete()
                .uri(START_PATH + "/tg-chat/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<ListLinkResponse> getAllLinks(Long tgChatId) {
        return webClient
                .get()
                .uri(START_PATH + "/links")
                .header("Tg-Chat-Id", tgChatId.toString())
                .retrieve()
                .bodyToMono(ListLinkResponse.class);
    }

    public Mono<LinkResponse> addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {
        return webClient
                .post()
                .uri(START_PATH + "links")
                .body((BodyInserter<?, ? super ClientHttpRequest>) addLinkRequest)
                .header("Tg-Chat-Id", tgChatId.toString())
                .retrieve()
                .bodyToMono(LinkResponse.class);
    }

    public Mono<LinkResponse> deleteLinks(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return webClient
                .method(HttpMethod.DELETE)
                .uri(START_PATH + "links")
                .header("Tg-Chat-Id", tgChatId.toString())
                .body((BodyInserter<?, ? super ClientHttpRequest>) removeLinkRequest)
                .retrieve()
                .bodyToMono(LinkResponse.class);
    }
}
