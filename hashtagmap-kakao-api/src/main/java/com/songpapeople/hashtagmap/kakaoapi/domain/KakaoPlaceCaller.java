package com.songpapeople.hashtagmap.kakaoapi.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoPlaceCaller {
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String AUTHORIZATION = "Authorization";
    private static final String KAKAO_AK = "KakaoAK ";
    private static final String CATEGORY_GROUP_CODE = "category_group_code";
    private static final String RECT = "rect";
    private static final String CATEGORY_URI = "/v2/local/search/category.json";

    private final String kakaoApiKey;
    private final WebClient webClient;

    public KakaoPlaceCaller(KakaoProperties kakaoProperties) {
        this.kakaoApiKey = kakaoProperties.getKey();
        webClient = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .defaultHeader(AUTHORIZATION, KAKAO_AK + kakaoApiKey)
                .build();
    }

    public Mono<KakaoPlaceDto> findPlaces(String category, Rect rect) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(CATEGORY_GROUP_CODE, category);
        params.add(RECT, rect.toKakaoFormat());

        Mono<KakaoPlaceDto> result = webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(CATEGORY_URI)
                        .queryParams(params)
                        .build())
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        throw new IllegalArgumentException("400에러" + clientResponse.statusCode()); // Todo
                    }

                    if (clientResponse.statusCode().is5xxServerError()) {
                        throw new IllegalArgumentException("500에러"); // Todo
                    }
                    return clientResponse.bodyToMono(KakaoPlaceDto.class);
                });
        return result;
    }
}
