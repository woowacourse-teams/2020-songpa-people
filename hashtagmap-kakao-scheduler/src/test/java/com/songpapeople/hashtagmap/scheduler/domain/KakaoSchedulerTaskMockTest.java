package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.*;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class KakaoSchedulerTaskMockTest {
    @Mock
    private KakaoApiService kakaoApiService;

    @Mock
    private ZoneRepository zoneRepository;

    @Autowired
    private PlaceRepository placeRepository;

    private KakaoSchedulerTask kakaoSchedulerTask;

    @BeforeEach
    private void setUp() {
        List<Place> places = Arrays.asList(
                Place.builder()
                        .kakaoId("123")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33", "127"), "서울시 송파구"))
                        .placeName("starbucks")
                        .placeUrl("http://starbucks.com")
                        .build(),
                Place.builder()
                        .kakaoId("124")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33.1", "127.1"), "서울시 송파구"))
                        .placeName("mahogani")
                        .placeUrl("http://mahogani.com")
                        .build()
        );
        placeRepository.saveAll(places);

        kakaoSchedulerTask = new KakaoSchedulerTask(zoneRepository, placeRepository, kakaoApiService);
    }

    @DisplayName("변경된 가게 데이터 저장 시 해당 가게 정보 업데이트")
    @Test
    void updateAndInsertPlaceTest() {
        List<Zone> zones = Collections.singletonList(
                Zone.builder()
                        .topLeft(new Point("33.02", "127.04"))
                        .bottomRight(new Point("33.04", "127.02"))
                        .district(new District("서울시 송파구"))
                        .isActivated(true)
                        .build()
        );
        when(zoneRepository.findByActivated()).thenReturn(zones);

        List<KakaoPlaceDto> kakaoPlaceDtos = Arrays.asList(
                new KakaoPlaceDto(null,
                        Arrays.asList(
                                Document.builder()
                                        .id("123")
                                        .categoryGroupCode("CE7")
                                        .placeName("woowacourse")
                                        .placeUrl("http://woowacourse.com")
                                        .roadAddressName("서울시 송파구")
                                        .x("33")
                                        .y("127")
                                        .build(),
                                Document.builder()
                                        .id("124")
                                        .categoryGroupCode("CE7")
                                        .placeName("mahogani")
                                        .placeUrl("http://mahogani.com")
                                        .roadAddressName("서울시 송파구")
                                        .x("33.1")
                                        .y("127.1")
                                        .build()
                        ))
        );
        when(kakaoApiService.findPlaces(any(), any())).thenReturn(kakaoPlaceDtos);

        kakaoSchedulerTask.collectData();

        List<Place> updatedPlaces = placeRepository.findAll();
        Place updatePlace = updatedPlaces.stream()
                .filter(place -> place.getKakaoId().equals("123"))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        Place nonUpdatePlace = updatedPlaces.stream()
                .filter(place -> place.getKakaoId().equals("124"))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        assertAll(
                () -> assertThat(updatedPlaces).hasSize(2),
                () -> assertThat(updatePlace.getPlaceName()).isEqualTo("woowacourse"),
                () -> assertThat(updatePlace.getPlaceUrl()).isEqualTo("http://woowacourse.com"),
                () -> assertThat(nonUpdatePlace.getPlaceName()).isEqualTo("mahogani")
        );
    }

    @AfterEach
    private void tearDown() {
        placeRepository.deleteAll();
    }
}
