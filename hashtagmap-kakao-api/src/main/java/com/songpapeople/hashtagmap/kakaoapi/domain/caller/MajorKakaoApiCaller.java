package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MajorKakaoApiCaller implements KakaoApiCaller {
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String CATEGORY_GROUP_CODE = "category_group_code";
    private static final String CATEGORY_URI = "/v2/local/search/category.json";
    private static final String RECT = "rect";
    private static final String PAGE = "page";

    private final RestTemplate restTemplate;

    public MajorKakaoApiCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public KakaoPlaceDto findPlaceByCategory(String category, Rect rect) {
        return findPlaceByCategory(category, rect, 1);
    }

    @Override
    public KakaoPlaceDto findPlaceByCategory(String category, Rect rect, int page) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + CATEGORY_URI)
                .queryParam(CATEGORY_GROUP_CODE, category)
                .queryParam(RECT, rect.toKakaoFormat())
                .queryParam(PAGE, Integer.toString(page));

        return restTemplate.getForObject(builder.toUriString(), KakaoPlaceDto.class);
    }
}
