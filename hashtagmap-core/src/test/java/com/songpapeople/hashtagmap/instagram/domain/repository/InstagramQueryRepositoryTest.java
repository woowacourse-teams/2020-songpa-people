package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class InstagramQueryRepositoryTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramQueryRepository instagramQueryRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("Hashtag 개수를 오름차순으로 정렬한다.")
    @Test
    void findAllHashtagCountByOrderAsc() {
        // given
        List<Long> hashtagCounts = Arrays.asList(3L, 2L, 1L);

        List<Instagram> instagrams = new ArrayList<>();
        for (Long hashtagCount : hashtagCounts) {
            instagrams.add(Instagram.builder()
                    .hashtagCount(hashtagCount)
                    .build());
        }
        instagramRepository.saveAll(instagrams);

        // when
        List<Long> actual = instagramQueryRepository.findAllHashtagCountByOrderAsc();

        // then
        Assertions.assertEquals(actual, Arrays.asList(1L, 2L, 3L));
    }

    @DisplayName("kakaoId로 인스타그램ID 가져오기")
    @Test
    void findByKakaoId() {
        String kakaoId = "999";
        Place place = Place.builder()
                .kakaoId(kakaoId).build();
        placeRepository.save(place);
        Instagram instagram = Instagram.builder()
                .place(place).build();
        instagramRepository.save(instagram);

        Instagram actual = instagramQueryRepository.findByKakaoId(kakaoId);

        assertAll(() -> {
            assertThat(actual.getId()).isEqualTo(instagram.getId());
            assertThat(actual.getKakaoId()).isEqualTo(place.getKakaoId());
        });
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
