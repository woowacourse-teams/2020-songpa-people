package com.songpapeople.hashtagmap.web;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                .when()
                .accept("image/png")
                .get("/img/icons/menu-bar-icon.png")
                .then()
                .contentType("image/png")
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
                .contentType("text/html")
                .statusCode(HttpStatus.OK.value())
                .assertThat().header("Content-Encoding", "gzip");
    }

    @DisplayName("리소스 응답 Etag 반환 테스트")
    @Test
    void etagResource() {
        Response response = given()
                .accept("image/png")
                .when()
                .get("/img/icons/menu-bar-icon.png");

        String etagValue = response.getHeader("Etag");

        assertThat(etagValue).isNotNull();
    }

    @DisplayName("리소스 두 번째 요청 304응답 테스트")
    @Test
    void etagRedirectResource() {
        Response response = given()
                .accept("image/png")
                .when()
                .get("/img/icons/menu-bar-icon.png");
        String etagValue = response.getHeader("Etag");

        given()
                .header("If-None-Match", etagValue)
                .when()
                .get("/img/icons/menu-bar-icon.png")
                .then()
                .log().all()
                .statusCode(HttpStatus.NOT_MODIFIED.value());
    }
}
