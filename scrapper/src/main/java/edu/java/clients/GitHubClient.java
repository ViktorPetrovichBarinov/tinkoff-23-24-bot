package edu.java.clients;

import edu.java.responses.github.GitHubRepositoryResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(WebClient wc) {
        this.webClient = wc;
    }

    public Mono<GitHubRepositoryResponse> fetchRepository(String owner, String repository) {
        return webClient
                .get()
                .uri("/repos/{owner}/{repo}", owner, repository)
                .retrieve()
                .bodyToMono(GitHubRepositoryResponse.class);

    }


}
