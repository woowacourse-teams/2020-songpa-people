package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForBlacklist;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForMaker;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForUpdate;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @DisplayName("instagramDto로 필요한 정보만 조회해오기")
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

        List<InstagramForMaker> instagramForMakers = instagramQueryRepository.findAllFetch();
        InstagramForMaker instagramForMaker = instagramForMakers.get(0);

        assertAll(() -> {
            assertThat(instagramForMaker.getHashtagCount()).isEqualTo(10000L);
            assertThat(instagramForMaker.getHashtagName()).isEqualTo("스타벅스");
            assertThat(instagramForMaker.getKakaoId()).isEqualTo("777");
            assertThat(instagramForMaker.getPlaceUrl()).isEqualTo("www");
            assertThat(instagramForMaker.getCategory()).isEqualTo(Category.CAFE);
        });
    }


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
        List<Long> actaul = instagramQueryRepository.findAllHashtagCountByOrderAsc();

        // then
        Assertions.assertEquals(actaul, Arrays.asList(1L, 2L, 3L));
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

        InstagramForUpdate instagramForUpdate = instagramQueryRepository.findByKakaoId(kakaoId);

        assertAll(() -> {
            assertThat(instagramForUpdate.getId()).isEqualTo(instagram.getId());
            assertThat(instagramForUpdate.getPlaceId()).isEqualTo(place.getId());
        });
    }

    @DisplayName("kakaoId로 인스타그램ID와 Place Id만 가져오고 업데이트 했을 때 null 값이 안들어가는지 확인")
    @Test
    @Transactional
    void findByKakaoIdAndUpdate() {
        String kakaoId = "999";
        Place place = Place.builder()
                .kakaoId(kakaoId).build();
        placeRepository.save(place);
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagName("oldName")
                .hashtagCount(1L)
                .build();
        instagramRepository.save(instagram);

        InstagramForUpdate instagramForUpdate = instagramQueryRepository.findByKakaoId(kakaoId);
        Instagram idOnlyInstagram = instagramForUpdate.toInstagram();
        idOnlyInstagram.updateInstagram("newName", 1000L);
        Instagram actual = instagramRepository.save(idOnlyInstagram);

        assertAll(() -> {
            assertThat(actual.getPlace().getKakaoId()).isEqualTo(kakaoId);
            assertThat(actual.getHashtagName()).isEqualTo("newName");
            assertThat(actual.getHashtagCount()).isEqualTo(1000L);
        });
    }

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

        List<InstagramForBlacklist> actual = instagramQueryRepository.findAllOrderByHashtagCountAndLimitBy(2);

        assertThat(actual).extracting(InstagramForBlacklist::getHashtagCount).isEqualTo(Arrays.asList(3L, 2L));
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
