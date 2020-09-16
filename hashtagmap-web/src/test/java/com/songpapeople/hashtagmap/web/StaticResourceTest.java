package com.songpapeople.hashtagmap.web;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourceTest {
    @LocalServerPort
    private int port;

    private static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("리소스 파일 캐싱 테스트")
    @Test
    void cacheResource() {
        given()
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get("/index.html")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .assertThat().header("Cache-Control", "max-age=31536000, public");
    }

    @DisplayName("리소스 파일 압축 테스트")
    @Test
    void gzipResource() {
        given()
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get("/index.html")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .assertThat().header("Content-Encoding", "gzip");
    }
}
