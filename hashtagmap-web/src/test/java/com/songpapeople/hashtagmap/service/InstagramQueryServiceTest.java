package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InstagramQueryServiceTest {

    private InstagramQueryService instagramQueryService;

    @Mock
    private InstagramRepository instagramRepository;

    @BeforeEach
    void setUp() {
        instagramQueryService = new InstagramQueryService(instagramRepository);
    }

    @DisplayName("findAllMarkers 테스트")
    @Test
    void findAllMarkers() {
        List<Instagram> instagrams = Arrays.asList(
                Instagram.builder()
                        .place(Place.builder()
                                .placeName("스타벅스")
                                .kakaoId("777")
                                .location(new Location(new Point("1", "2"), null))
                                .build())
                        .id(1L)
                        .hashtagCount(10000L)
                        .build()
        );
        given(instagramRepository.findAllFetch()).willReturn(instagrams);

        List<MarkerResponse> actual = instagramQueryService.findAllMarkers();
        MarkerResponse expected = MarkerResponse.from(
                Instagram.builder()
                        .place(Place.builder()
                                .placeName("스타벅스")
                                .kakaoId("777")
                                .location(new Location(new Point("1", "2"), null))
                                .build())
                        .id(1L)
                        .hashtagCount(10000L)
                        .build()
        );

        // TODO: 2020-08-05 TagLevel 로직 정해진 후 테스트 수정
        assertThat(actual.get(0)).isEqualToIgnoringGivenFields(expected, "tagLevel");
    }
}