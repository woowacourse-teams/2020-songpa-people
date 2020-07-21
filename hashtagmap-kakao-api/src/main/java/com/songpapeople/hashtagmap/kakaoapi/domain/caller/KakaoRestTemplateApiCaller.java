package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class KakaoRestTemplateApiCaller {
    private static final String RECT = "rect";
    private static final String PAGE = "page";

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    public KakaoRestTemplateApiCaller(RestTemplate restTemplate, KakaoProperties kakaoProperties) {
        this.restTemplate = restTemplate;
        this.kakaoProperties = kakaoProperties;
    }

    public KakaoPlaceDto findPlaceByCategory(String category, Rect rect, int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(kakaoProperties.getCategoryUrl())
                .queryParam(kakaoProperties.getCategoryGroupCode(), category)
                .queryParam(RECT, rect.toKakaoUriFormat())
                .queryParam(PAGE, Integer.toString(page))
                .build();
        return restTemplate.getForObject(uri.toUriString(), KakaoPlaceDto.class);
    }

    public boolean isLessOrEqualTotalCount(KakaoPlaceDto kakaoPlaceDto) {
        int totalCount = kakaoPlaceDto.getTotalCount();
        return (kakaoProperties.getMaxDocumentCount() * kakaoProperties.getMaxPageableCount()) >= totalCount;
    }
}
