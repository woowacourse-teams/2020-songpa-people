package com.songpapeople.hashtagmap.repository;

import com.songpapeople.hashtagmap.dto.InstagramForMarker;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class InstagramWebQueryRepositoryTest {

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramWebQueryRepository instagramWebQueryRepository;

    @DisplayName("화면 출력에 필요한 InstagramDto 조회하기")
    @Test
    void findAllFetch() {
        Place place = Place.builder()
                .placeName("스타벅스")
                .kakaoId("777")
                .placeUrl("www")
                .location(new Location(new Point("34.123", "126.124"), null))
                .category(Category.CAFE)
                .build();
        placeRepository.save(place);
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagName("스타벅스")
                .hashtagCount(10000L)
                .build();
        instagramRepository.save(instagram);

        List<InstagramForMarker> instagramForMarkers = instagramWebQueryRepository.findAllFetch();
        InstagramForMarker instagramForMarker = instagramForMarkers.get(0);

        assertAll(() -> {
            assertThat(instagramForMarker.getHashtagCount()).isEqualTo(10000L);
            assertThat(instagramForMarker.getHashtagName()).isEqualTo("스타벅스");
            assertThat(instagramForMarker.getKakaoId()).isEqualTo("777");
            assertThat(instagramForMarker.getPlaceUrl()).isEqualTo("www");
            assertThat(instagramForMarker.getCategory()).isEqualTo(Category.CAFE);
        });
    }

    @AfterEach
    public void tearDown() {
        placeRepository.deleteAll();
        instagramRepository.deleteAll();
    }
}