package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class PlaceRepositoryTest {
    private static final String ROAD_ADDRESS_NAME = "서울시 송파구";

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("존재하지 않는 Place 저장하기")
    @Test
    void updateInsertQueryTest_insert() {
        Place place = Place.builder()
                .kakaoId("1")
                .category(Category.CAFE)
                .location(new Location(new Point("33", "127"), ROAD_ADDRESS_NAME))
                .placeName("starbucks2")
                .build();


        placeRepository.updateAndInsert(Collections.singletonList(place));

        List<Place> savedPlaces = placeRepository.findAll();
        assertThat(savedPlaces).hasSize(1);
        assertThat(savedPlaces.get(0).getKakaoId()).isEqualTo(place.getKakaoId());
    }

    private void setUp() {
        List<Place> places = Arrays.asList(
                Place.builder()
                        .kakaoId("1")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33", "127"), ROAD_ADDRESS_NAME))
                        .placeName("starbucks")
                        .build(),
                Place.builder()
                        .kakaoId("2")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33.5", "127.5"), ROAD_ADDRESS_NAME))
                        .placeName("mahogani")
                        .build()
        );
        placeRepository.saveAll(places);
    }

    @DisplayName("존재하는 Place 갱신하기")
    @Test
    void updateInsertQueryTest_update() {
        List<Place> duplicateKakaoIdPlaces = Arrays.asList(
                Place.builder()
                        .kakaoId("1")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33", "127"), ROAD_ADDRESS_NAME))
                        .placeName("starbucks2")
                        .build(),
                Place.builder()
                        .kakaoId("2")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33.5", "127.5"), ROAD_ADDRESS_NAME))
                        .placeName("mahogani2")
                        .build(),
                Place.builder()
                        .kakaoId("3")
                        .category(Category.CAFE)
                        .location(new Location(new Point("33.2", "127.2"), ROAD_ADDRESS_NAME))
                        .placeName("cu")
                        .build()
        );
        placeRepository.updateAndInsert(duplicateKakaoIdPlaces);

        assertAll(
                () -> assertThat(placeRepository.findByPlaceName("starbucks2").get(0).getKakaoId()).isEqualTo("1"),
                () -> assertThat(placeRepository.findByPlaceName("mahogani2").get(0).getKakaoId()).isEqualTo("2"),
                () -> assertThat(placeRepository.findByPlaceName("cu").get(0).getKakaoId()).isEqualTo("3")
        );
    }
    @AfterEach
    private void tearDown() {
        placeRepository.deleteAll();
    }
}
