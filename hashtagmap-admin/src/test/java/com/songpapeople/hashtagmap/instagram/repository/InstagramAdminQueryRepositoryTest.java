package com.songpapeople.hashtagmap.instagram.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.dto.InstagramForBlacklist;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InstagramAdminQueryRepositoryTest {

    @Autowired
    private InstagramAdminQueryRepository instagramAdminQueryRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @DisplayName("Hashtag count 역순으로 정렬해서 limit만큼 조회하기")
    @Test
    void findAllOrderByHashtagCountAndLimitBy() {
        Place place = Place.builder()
                .build();
        placeRepository.save(place);
        List<Instagram> instagrams = Arrays.asList(
                Instagram.builder()
                        .place(place)
                        .hashtagCount(1L)
                        .build(),
                Instagram.builder()
                        .place(place)
                        .hashtagCount(3L)
                        .build(),
                Instagram.builder()
                        .place(place)
                        .hashtagCount(2L)
                        .build()
        );
        instagramRepository.saveAll(instagrams);

        List<InstagramForBlacklist> actual = instagramAdminQueryRepository.findAllOrderByHashtagCountAndLimitBy(2);

        assertThat(actual).extracting(InstagramForBlacklist::getHashtagCount).isEqualTo(Arrays.asList(3L, 2L));
    }

    @AfterEach
    public void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}