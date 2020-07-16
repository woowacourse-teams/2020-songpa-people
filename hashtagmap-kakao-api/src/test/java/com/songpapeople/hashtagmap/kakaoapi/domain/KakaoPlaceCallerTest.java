package com.songpapeople.hashtagmap.kakaoapi.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoPlaceCallerTest {
    @Autowired
    private KakaoPlaceCaller kakaoPlaceCaller;

    @Disabled
    @DisplayName("정의한 범위 내에서 카페 카테고리에 대한 Kakao API 호출")
    @Test
    public void KakaoPlaceCallerTest() {
        Rect rect = new Rect(new Latitude(37.569449), new Longitude(126.979533), 0.02);
        KakaoPlaceDto result = kakaoPlaceCaller.findPlaces("CE7", rect);

        Integer totalCount = result.getMeta().getTotalCount();
        int documentsSize = result.getDocuments().size();
        String categoryGroupCode = result.getDocuments().get(0).getCategoryGroupCode();

        assertThat(result).isNotNull();
        assertThat(totalCount).isNotEqualTo(0);
        assertThat(documentsSize).isNotEqualTo(0);
        assertThat(categoryGroupCode).isEqualTo("CE7");
    }

    @DisplayName("사각형 범위와 카테고리를 입력했을 때, 기준 이상의 가게 데이터를 가지고 있다면 재귀한다.")
    @Test
    void name() {

    }
}