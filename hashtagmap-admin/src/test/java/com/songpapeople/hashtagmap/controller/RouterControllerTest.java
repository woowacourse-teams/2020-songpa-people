package com.songpapeople.hashtagmap.controller;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RouterControllerTest {

    @LocalServerPort
    private int port;

    private static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("'/'로 get요청")
    @Test
    void indexPage() {
        given()
                .when()
                .get("/")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }
}