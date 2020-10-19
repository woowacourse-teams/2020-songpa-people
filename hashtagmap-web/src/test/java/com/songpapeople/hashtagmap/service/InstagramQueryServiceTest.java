package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InstagramQueryServiceTest {

    @Autowired
    private InstagramQueryService instagramQueryService;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private TagLevelRepository tagLevelRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("findAllMarkers 테스트")
    @Test
    void findAllMarkers() {
        Place place = Place.builder()
                .placeName("스타벅스")
                .kakaoId("198")
                .location(new Location(new Point("34", "126"), "서울시 강남구"))
                .category(Category.CAFE)
                .build();
        placeRepository.save(place);
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(9999L)
                .build();
        instagramRepository.save(instagram);
        List<TagLevel> tagLevels = Arrays.asList(
                new TagLevel(1L, 100L, 5000L),
                new TagLevel(2L, 5001L, 10000L)
        );
        tagLevelRepository.saveAll(tagLevels);

        List<MarkerResponse> actual = instagramQueryService.findAllMarkers();
        MarkerResponse expected = MarkerResponse.of(
                Instagram.builder()
                        .place(place)
                        .id(instagram.getId())
                        .hashtagCount(instagram.getHashtagCount())
                        .build(),
                2L
        );

        assertThat(actual.size()).isEqualTo(1L);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
        tagLevelRepository.deleteAll();
    }
}