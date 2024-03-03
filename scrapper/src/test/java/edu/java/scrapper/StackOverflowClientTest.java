package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import edu.java.responses.github.GitHubRepositoryResponse;
import edu.java.responses.stackoverflow.Owner;
import edu.java.responses.stackoverflow.StackOverflowQuestionsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StackOverflowClientTest {

    private WireMockServer wireMockServer;
    private StackOverflowClient stackOverflowClient;

    @BeforeEach
    public void setUp() {
        this.wireMockServer = new WireMockServer();
        this.wireMockServer.start();


        WebClient webClient = WebClient
                .builder()
                .baseUrl(this.wireMockServer.baseUrl())
                .build();

        this.stackOverflowClient = new StackOverflowClient(webClient);
    }

    @AfterEach
    public void tearDown() {
        this.wireMockServer.stop();
    }


    @Test
    @DisplayName("test data - stackoverflow-haskell-quest.json")
    public void test1() throws IOException {
        // Настройка эмуляции ответа от GitHub API
        this.wireMockServer.stubFor(get(urlEqualTo("/questions/52349820?site=stackoverflow"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(Files.readString(Paths.get("src/test/resources/stackoverflow-haskell-quest.json")))));  // Предположим, что у вас есть файл с ожидаемым JSON-ответом

        // Вызываем метод fetchRepository и проверяем результат
        Mono<StackOverflowQuestionsResponse> resultMono = this.stackOverflowClient.fetchQuestion(52349820);
        StackOverflowQuestionsResponse result = resultMono.block();
        assertThat(result).isNotNull();

        Owner owner = result.items().getFirst().getOwner();
        assertThat(owner).isNotNull();

        assertThat(owner.getAccountId()).isEqualTo(14295498);
        assertThat(owner.getUserId()).isEqualTo(10326304);
        assertThat(owner.getDisplayName()).isEqualTo("H. Dong");
        assertThat(owner.getLink()).isEqualTo("https://stackoverflow.com/users/10326304/h-dong");


        StackOverflowQuestionsResponse.QuestionInformation questionInformation = result.items().getFirst();

        assertThat(questionInformation.getQuestionId()).isEqualTo(52349820);
        assertThat(questionInformation.getAnswerId()).isEqualTo(52349907);
        assertThat(questionInformation.getLastActivityDate()).isEqualTo("2018-09-16T12:56:24Z");
    }
}
