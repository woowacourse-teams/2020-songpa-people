package com.songpapeople.hashtagmap.scheduler.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KakaoSchedulerServiceTest {
    private KakaoSchedulerService kakaoSchedulerService;

    @Mock
    private KakaoApiService kakaoApiService;

    @BeforeEach
    private void setUp() {
        this.kakaoSchedulerService = new KakaoSchedulerService(kakaoApiService);
    }

    @DisplayName("Zone을 Rect로 변환한다")
    @Test
    public void zoneToRectTest() {
        Zone zone = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(new District("서울시 송파구", true))
                .build();
        Rect rect = Rect.byOffset(new Latitude(33), new Longitude(130), 2);

        assertThat(kakaoSchedulerService.zoneToRect(zone)).isEqualTo(rect);
    }

    @DisplayName("Kakao API로 가게 정보 찾기")
    @Test
    public void apiCallTest() {
        List<KakaoPlaceDto> results = Arrays.asList(
                new KakaoPlaceDto(null, null),
                new KakaoPlaceDto(null, null)
        );
        when(kakaoApiService.findPlaces(anyString(), any())).thenReturn(results);

        Rect rect = Rect.byOffset(new Latitude(33), new Longitude(130), 2);
        List<KakaoPlaceDto> places = kakaoSchedulerService.findPlacesByRect(Category.CAFE, rect);
        assertThat(places).hasSize(results.size());
    }
}
