package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.clients.GitHubClient;
import edu.java.responses.github.GitHubRepositoryResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GitHubClientTest {
    private WireMockServer wireMockServer;
    private GitHubClient gitHubClient;

    @BeforeEach
    public void setUp() {
        this.wireMockServer = new WireMockServer();
        this.wireMockServer.start();


        WebClient webClient = WebClient
                .builder()
                .baseUrl(this.wireMockServer.baseUrl())
                .build();

        this.gitHubClient = new GitHubClient(webClient);
    }



    @AfterEach
    public void tearDown() {
        this.wireMockServer.stop();
    }


    @Test
    @DisplayName("test data - tink_java_second_sem.json")
    public void test1() throws IOException {
        // Настройка эмуляции ответа от GitHub API
        this.wireMockServer.stubFor(get(urlEqualTo("/repos/ViktorPetrovichBarinov/tinkoff-23-24-second-sem"))
                        .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(Files.readString(Paths.get("src/test/resources/tink_java_second_sem.json")))));  // Предположим, что у вас есть файл с ожидаемым JSON-ответом

        // Вызываем метод fetchRepository и проверяем результат
        Mono<GitHubRepositoryResponse> resultMono = this.gitHubClient.fetchRepository("ViktorPetrovichBarinov", "tinkoff-23-24-second-sem");
        GitHubRepositoryResponse result = resultMono.block();
//
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(758574037);
        assertThat(result.getName()).isEqualTo("tinkoff-23-24-second-sem");
        assertThat(result.getFullName()).isEqualTo("ViktorPetrovichBarinov/tinkoff-23-24-second-sem");
        assertThat(result.getHtmlUrl()).isEqualTo("https://github.com/ViktorPetrovichBarinov/tinkoff-23-24-second-sem");
        assertThat(result.getApiUrl()).isEqualTo("https://api.github.com/repos/ViktorPetrovichBarinov/tinkoff-23-24-second-sem");
        assertThat(result.getCreatedAt()).isEqualTo("2024-02-16T16:00:32Z");
        assertThat(result.getUpdatedAt()).isEqualTo("2024-02-24T16:51:08Z");
        assertThat(result.getPushedAt()).isEqualTo("2024-02-24T16:48:34Z");
    }

    @Test
    @DisplayName("error request")
    public void test2() throws IOException {
        // Настройка эмуляции ответа от GitHub API
        this.wireMockServer.stubFor(get(urlEqualTo("/repos/ViktorPetrovichBarinov/error-repo"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody(Files.readString(Paths.get("src/test/resources/tink_java_second_sem.json")))));

        // Вызываем метод fetchRepository и проверяем результат
        Mono<GitHubRepositoryResponse> resultMono = this.gitHubClient.fetchRepository("ViktorPetrovichBarinov", "error-repo");

        StepVerifier.create(resultMono)
                .expectError(WebClientResponseException.NotFound.class)  // Ожидаем ошибку 404
                .verify();
    }
}
