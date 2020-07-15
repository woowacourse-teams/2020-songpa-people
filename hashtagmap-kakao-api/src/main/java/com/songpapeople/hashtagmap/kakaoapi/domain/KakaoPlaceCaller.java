package com.songpapeople.hashtagmap.kakaoapi.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.exception.KakaoExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KakaoPlaceCaller {

    private static final int DEFAULT_PAGE = 1;
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String CATEGORY_GROUP_CODE = "category_group_code";
    private static final String CATEGORY_URI = "/v2/local/search/category.json";
    private static final String RECT = "rect";
    private static final String PAGE = "page";

    private final HttpEntity<HttpHeaders> entity;
    private final RestTemplate restTemplate;

    public KakaoPlaceCaller(KakaoProperties kakaoProperties, RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new KakaoExceptionHandler())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoProperties.getKey());
        entity = new HttpEntity<>(headers);
    }

    public KakaoPlaceDto findPlaces(String category, Rect rect) {
        return findPlaces(category, rect, DEFAULT_PAGE);
    }

    public KakaoPlaceDto findPlaces(String category, Rect rect, int page) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + CATEGORY_URI)
                .queryParam(CATEGORY_GROUP_CODE, category)
                .queryParam(RECT, rect.toKakaoFormat())
                .queryParam(PAGE, Integer.toString(page));

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody(), KakaoPlaceDto.class);
        } catch (JsonProcessingException exception) {
            exception.getStackTrace();
            throw new RuntimeException();
        }
    }
}
