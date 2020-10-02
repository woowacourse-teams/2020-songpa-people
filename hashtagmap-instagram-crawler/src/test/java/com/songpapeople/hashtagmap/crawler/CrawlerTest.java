package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class CrawlerTest {
    private final Crawler crawler = new Crawler();
    private ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer();
    }

    @DisplayName("크롤링 정상 동작 테스트")
    @Test
    void crawlingTest() {
        new MockServerClient("localhost", mockServer.getPort())
                .when(request()
                        .withMethod("GET"))
                .respond(response()
                        .withStatusCode(200));

        String body = crawler.crawl("http://localhost:" + mockServer.getPort());
        assertThat(body).isNotNull();
    }

    @DisplayName("잘못된 url 크롤링 시 예외 테스트")
    @Test
    void crawlingUrlExceptionTest() {
        new MockServerClient("localhost", mockServer.getPort())
                .when(request()
                        .withMethod("GET"))
                .respond(response()
                        .withStatusCode(400));

        assertThatThrownBy(() -> crawler.crawl("http://localhost:" + mockServer.getPort() + "/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.URL_NOT_CONNECT.getMessage());
    }

    @DisplayName("찾을 수 없는 URL 예외 테스트")
    @Test
    void notFoundUrlExceptionTest() {
        final String KEY_WORD = "절대로_없을_검색어";

        new MockServerClient("localhost", mockServer.getPort())
                .when(request()
                        .withMethod("GET")
                        .withPath("/explore/tags/" + KEY_WORD + "/"))
                .respond(response()
                        .withStatusCode(429));

        assertThatThrownBy(() -> crawler.crawl("http://localhost:" + mockServer.getPort() + "/explore/tags/" + KEY_WORD + "/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.NOT_FOUND_URL.getMessage());
    }

    @DisplayName("너무 많은 요청을 보내 발생하는 429 예외 테스트")
    @Test
    void tooManyRequestExceptionTest() {
        final String KEY_WORD = "daily";

        new MockServerClient("localhost", mockServer.getPort())
                .when(request()
                        .withMethod("GET")
                        .withPath("/explore/tags/" + KEY_WORD + "/"))
                .respond(response()
                        .withStatusCode(429));

        assertThatThrownBy(() -> crawler.crawl("http://localhost:" + mockServer.getPort() + "/explore/tags/" + KEY_WORD + "/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.TOO_MANY_REQUEST.getMessage());
    }

    @AfterEach
    void shutDown() {
        mockServer.stop();
    }
}
