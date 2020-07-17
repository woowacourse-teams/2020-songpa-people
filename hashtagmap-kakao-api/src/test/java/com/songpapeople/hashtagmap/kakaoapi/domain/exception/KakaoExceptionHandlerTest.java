package com.songpapeople.hashtagmap.kakaoapi.domain.exception;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RestClientTest
public class KakaoExceptionHandlerTest {
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String CATEGORY_GROUP_CODE = "category_group_code";

    @Autowired
    private RestTemplateBuilder builder;

    private MockRestServiceServer server;
    private RestTemplate restTemplate;

    private static Stream<Arguments> provideErrorCode() {
        return Stream.of(
                Arguments.of(HttpStatus.BAD_REQUEST),
                Arguments.of(HttpStatus.UNAUTHORIZED),
                Arguments.of(HttpStatus.FORBIDDEN),
                Arguments.of(HttpStatus.INTERNAL_SERVER_ERROR),
                Arguments.of(HttpStatus.BAD_GATEWAY),
                Arguments.of(HttpStatus.SERVICE_UNAVAILABLE)
        );
    }

    @BeforeEach
    private void setUp() {
        restTemplate = this.builder
                .errorHandler(new KakaoExceptionHandler())
                .build();
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @ParameterizedTest
    @MethodSource("provideErrorCode")
    public void badRequestTest(HttpStatus status) {
        this.server
                .expect(ExpectedCount.once(), requestTo(BASE_URL + CATEGORY_GROUP_CODE))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(status));

        assertThatThrownBy(() -> restTemplate.getForObject(BASE_URL + CATEGORY_GROUP_CODE, KakaoPlaceDto.class))
                .isInstanceOf(KakaoException.class)
                .extracting("statusCode")
                .isEqualTo(status.value());
    }
}
