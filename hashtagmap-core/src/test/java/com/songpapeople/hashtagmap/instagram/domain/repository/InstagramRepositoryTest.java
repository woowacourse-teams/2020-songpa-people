package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InstagramRepositoryTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("place로 instagram 찾는 기능 테스트")
    @Test
    void findByPlace() {
        Place place = Place.builder()
                .placeName("place")
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .build();
        placeRepository.save(place);
        instagramRepository.save(instagram);

        Instagram result = instagramRepository.findByPlace(place).get();

        assertThat(result.getId()).isEqualTo(instagram.getId());
    }
}
