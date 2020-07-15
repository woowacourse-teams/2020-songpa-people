package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.KakaoPlaceCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.Position;
import com.songpapeople.hashtagmap.kakaoapi.domain.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.divider.RectDivider;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Meta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(kakaoPlaceCaller.findPlaces(anyString(), any())).thenReturn(kakaoPlaceDto);
        when(kakaoPlaceCaller.findPlaces(anyString(), any(), anyInt())).thenReturn(kakaoPlaceDto);
        Rect rect = new Rect(new Position(37.497366, 127.113847), new Position(37.519033, 127.069661));
        List<KakaoPlaceDto> result = kakaoService.findPlaces("CE7", rect);

        int times = RectDivider.divide(rect, 0.02).size();
        assertThat(result).hasSizeBetween(times, times * 15 * 45);
        verify(kakaoPlaceCaller, times(times)).findPlaces(any(), any());
    }
}