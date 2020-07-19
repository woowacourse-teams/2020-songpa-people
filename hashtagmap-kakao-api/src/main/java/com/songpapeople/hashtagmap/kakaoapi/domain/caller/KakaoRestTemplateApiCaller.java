package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class KakaoRestTemplateApiCaller implements KakaoApiCaller {
    private static final String CATEGORY_URI = "/v2/local/search/category.json";
    private static final String CATEGORY_GROUP_CODE = "category_group_code";
    private static final String RECT = "rect";
    private static final String PAGE = "page";
    private static final int KAKAKO_MAX_DOCUMENTS_COUNT = 15;
    private static final int KAKAO_MAX_PAGEABLE_COUNT = 45;

    private final int maxDocumentsCount;
    private final int maxPageableCount;
    private final RestTemplate restTemplate;

    public KakaoRestTemplateApiCaller(RestTemplate restTemplate) {
        this(restTemplate, KAKAKO_MAX_DOCUMENTS_COUNT, KAKAO_MAX_PAGEABLE_COUNT);
    }

    public KakaoRestTemplateApiCaller(RestTemplate restTemplate, int maxDocumentsCount, int maxPageableCount) {
        this.restTemplate = restTemplate;
        this.maxDocumentsCount = maxDocumentsCount;
        this.maxPageableCount = maxPageableCount;
    }

    @Override
    public KakaoPlaceDto findPlaceByCategory(String category, Rect rect, int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(CATEGORY_URI)
                .queryParam(CATEGORY_GROUP_CODE, category)
                .queryParam(RECT, rect.toKakaoFormat())
                .queryParam(PAGE, Integer.toString(page))
                .build();
        return restTemplate.getForObject(uri.toUriString(), KakaoPlaceDto.class);
    }

    @Override
    public boolean isLessOrEqualTotalCount(KakaoPlaceDto kakaoPlaceDto) {
        int totalCount = kakaoPlaceDto.getMeta().getTotalCount();
        return (this.maxDocumentsCount * this.maxPageableCount) >= totalCount;
    }
}
