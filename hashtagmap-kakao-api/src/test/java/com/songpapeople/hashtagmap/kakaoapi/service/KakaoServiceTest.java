package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.KakaoPlaceCaller;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KakaoServiceTest {
    @Mock
    private KakaoPlaceCaller kakaoPlaceCaller;

    private KakaoService kakaoService;

    @BeforeEach
    private void setUp() {
        kakaoService = new KakaoService(kakaoPlaceCaller);
    }

    @DisplayName("Big Rect와 카테고리에 대한 가게 정보를 가져오기")
    @Test
    public void KakaoServiceTest() {
        Meta meta = Meta.builder()
                .pageableCount(45)
                .build();
        KakaoPlaceDto kakaoPlaceDto = new KakaoPlaceDto(meta, Collections.singletonList(new Document()));
        lenient().when(kakaoPlaceCaller.findPlaces(anyString(), any())).thenReturn(kakaoPlaceDto);
        lenient().when(kakaoPlaceCaller.findPlaces(anyString(), any(), anyInt())).thenReturn(kakaoPlaceDto);
        Rect rect = new Rect(new Latitude(37.497366), new Longitude(127.113847), BigDecimal.valueOf(0.1));
        List<KakaoPlaceDto> result = kakaoService.findPlaces("CE7", rect);

        int times = RectDivider.divide(rect, BigDecimal.valueOf(0.02)).size();
        assertThat(result).hasSizeBetween(times, times * 15 * 45);
        verify(kakaoPlaceCaller, times(times)).findPlaces(any(), any());
    }
}