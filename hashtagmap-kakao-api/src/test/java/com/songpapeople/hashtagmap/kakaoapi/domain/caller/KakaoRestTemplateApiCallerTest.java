package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

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
public class KakaoRestTemplateApiCallerTest {
    private static final String CATEGORY_GROUP_CODE = "CE7";

    @Autowired
    private KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;

    @Disabled
    @DisplayName("InitialRect 내에서 카페 카테고리에 대한 Kakao API 통신 확인")
    @Test
    public void KakaoPlaceCallerTest() {
        Rect rect = Rect.byOffset(new Latitude(37.569449), new Longitude(126.979533), 0.02);
        KakaoPlaceDto result = kakaoRestTemplateApiCaller.findPlaceByCategory(CATEGORY_GROUP_CODE, rect, 1);

        Integer totalCount = result.getMeta().getTotalCount();
        int documentsSize = result.getDocuments().size();
        String categoryGroupCode = result.getDocuments().get(0).getCategoryGroupCode();

        assertThat(result).isNotNull();
        assertThat(totalCount).isNotEqualTo(0);
        assertThat(documentsSize).isNotEqualTo(0);
        assertThat(categoryGroupCode).isEqualTo(CATEGORY_GROUP_CODE);
    }
}