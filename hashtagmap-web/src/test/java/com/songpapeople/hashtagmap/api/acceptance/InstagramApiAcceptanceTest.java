package com.songpapeople.hashtagmap.api.acceptance;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstagramApiAcceptanceTest {
    @LocalServerPort
    public int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    //@formatter:off
    @DisplayName("마커를 클릭했을 때, 해당 place의 instagramPost정보를 내려주는 api 인수테스트")
    @Test
    void getInstagramPost() {
        final int instagramId = 1;

        given()
        .when()
                .get("/instagram/" + instagramId + "/post")
        .then()
                .log().all()
                .extract()
                .as(new TypeRef<CustomResponse<InstagramPostResponse>>() {});
    }
}
