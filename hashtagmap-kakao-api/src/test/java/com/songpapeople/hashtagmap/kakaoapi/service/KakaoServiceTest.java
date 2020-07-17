package com.songpapeople.hashtagmap.kakaoapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoProperties;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateBuilder;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.songpapeople.hashtagmap.kakaoapi.domain.KakaoPlaceDtoFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class KakaoServiceTest {
    private static final String CATEGORY_GROUP_CODE = "CE7";

    private ObjectMapper objectMapper;
    private KakaoProperties kakaoProperties;
    private RestTemplate restTemplate;
    private KakaoApiCaller kakaoApiCaller;
    private KakaoService kakaoService;
    private MockRestServiceServer server;

    @BeforeEach
    private void setUp() {
        this.objectMapper = new ObjectMapper();
        this.kakaoProperties = new KakaoProperties();
        this.kakaoProperties.setKey("test_key");
        this.restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties).build();
        this.kakaoApiCaller = new KakaoRestTemplateApiCaller(restTemplate, MAX_DOCUMENT_COUNT, MAX_PAGEABLE_COUNT);
        this.kakaoService = new KakaoService(kakaoApiCaller);
        this.server = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
    }

    @DisplayName("주어진 Rect에 해당하는 가게 데이터 수집")
    @Test
    void findPlacesTest() throws JsonProcessingException {
        for (Rect rect : rects) {
            stubServer(rect);
        }
        for (Rect rect : dividedRects) {
            stubServer(rect);
        }
        List<KakaoPlaceDto> places = kakaoService.findPlaces(CATEGORY_GROUP_CODE, initialRect);

        assertThat(places).hasSize(7);
    }

    private void stubServer(Rect rect) throws JsonProcessingException {
        int pageableCount = createPageableCount(totalCounts.get(rect));
        for (int i = 1; i <= pageableCount; i++) {
            KakaoPlaceDto kakaoPlaceDto = createPlaceDto(totalCounts.get(rect), i);
            String kakaoPlaceDtoJson = objectMapper.writeValueAsString(kakaoPlaceDto);
            server.expect(requestTo(createUri(rect, i)))
                    .andExpect(method(HttpMethod.GET))
                    .andExpect(MockRestRequestMatchers.header("Authorization", "KakaoAK " + kakaoProperties.getKey()))
                    .andRespond(withSuccess(kakaoPlaceDtoJson, MediaType.APPLICATION_JSON));
        }
    }

    // TODO: 2020/07/17 uri를 생성하는 방법 찾기
    private String createUri(Rect rect, int page) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://dapi.kakao.com/v2/local/search/category.json?");
        builder.append("category_group_code=" + CATEGORY_GROUP_CODE);
        builder.append("&rect=" + rect.toKakaoFormat());
        builder.append("&page=" + page);
        return builder.toString();
    }
}