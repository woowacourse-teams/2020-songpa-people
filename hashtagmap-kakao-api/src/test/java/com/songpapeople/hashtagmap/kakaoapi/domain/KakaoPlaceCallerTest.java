package com.songpapeople.hashtagmap.kakaoapi.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoPlaceCallerTest {
    @Autowired
    private KakaoProperties kakaoProperties;

    private KakaoPlaceCaller kakaoPlaceCaller;

    @BeforeEach
    public void setUp() {
        kakaoPlaceCaller = new KakaoPlaceCaller(kakaoProperties);
    }

    @Disabled
    @DisplayName("정의한 범위 내에서 카페 카테고리에 대한 Kakao API 호출")
    @Test
    public void KakaoPlaceCallerTest() {
        Position left = new Position(37.569449, 126.979533);
        Rect rect = new Rect(left, 0.03, 0.01);
        KakaoPlaceDto result = kakaoPlaceCaller.findPlaces("CE7", rect).block();

        Integer totalCount = result.getMeta().getTotalCount();
        int documentsSize = result.getDocuments().size();
        String categoryGroupCode = result.getDocuments().get(0).getCategoryGroupCode();

        assertThat(result).isNotNull();
        assertThat(totalCount).isNotEqualTo(0);
        assertThat(documentsSize).isNotEqualTo(0);
        assertThat(categoryGroupCode).isEqualTo("CE7");
    }


}