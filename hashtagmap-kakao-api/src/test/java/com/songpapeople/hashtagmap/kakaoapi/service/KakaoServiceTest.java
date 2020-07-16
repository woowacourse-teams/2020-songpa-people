package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Meta;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.RectDivider;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static com.songpapeople.hashtagmap.kakaoapi.service.KakaoPlaceDtoFixture.MAX_PAGEABLE_COUNT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KakaoServiceTest {
    private static final String CATEGORY_GROUP_CODE = "CE7";
    @Mock
    private KakaoApiCaller kakaoApiCaller;

    private KakaoService kakaoService;

    @BeforeEach
    private void setUp() {
        kakaoService = new KakaoService(kakaoApiCaller);
    }

    @DisplayName("Offset 조정이 없을 때, 요청 횟수 확인")
    @Test
    public void KakaoServiceTest() {
        // given
        Meta meta = Meta.builder()
                .pageableCount((int) (Math.random() * MAX_PAGEABLE_COUNT) + 1)
                .build();
        KakaoPlaceDto kakaoPlaceDto = new KakaoPlaceDto(meta, Collections.singletonList(new Document()));
        when(kakaoApiCaller.findPlaceByCategory(anyString(), any(), anyInt())).thenReturn(kakaoPlaceDto);

        // when
        Rect rect = new Rect(new Latitude(37), new Longitude(127), 0.1);
        kakaoService.findPlaces(CATEGORY_GROUP_CODE, rect);

        // then
        int expectRectCount = RectDivider.divide(rect, BigDecimal.valueOf(0.02)).size();
        verify(kakaoApiCaller, atLeast(expectRectCount)).findPlaceByCategory(anyString(), any(), anyInt());
        verify(kakaoApiCaller, atMost(expectRectCount * MAX_PAGEABLE_COUNT))
                .findPlaceByCategory(anyString(), any(), anyInt());
    }
}