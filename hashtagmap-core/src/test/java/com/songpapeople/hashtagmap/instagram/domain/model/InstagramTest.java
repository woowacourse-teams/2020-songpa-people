package com.songpapeople.hashtagmap.instagram.domain.model;

import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InstagramTest {

    @DisplayName("Instagram haschtag count, hashtag name 업데이트 테스트")
    @Test
    void updateInstagram() {
        Instagram instagram = Instagram.builder()
                .hashtagCount(200L)
                .hashtagName("오아시스")
                .build();
        instagram.updateInstagram("오아시스강남", 1000L);

        assertAll(() -> {
            assertThat(instagram.getHashtagCount()).isEqualTo(1000L);
            assertThat(instagram.getHashtagName()).isEqualTo("오아시스강남");
        });
    }

    @DisplayName("Instagram에서 place name 가져오기")
    @Test
    void getPlaceName() {
        Place place = Place.builder()
                .placeName("오아시스").build();
        Instagram instagram = Instagram.builder()
                .place(place).build();

        assertThat(instagram.getPlaceName()).isEqualTo("오아시스");
    }

    @DisplayName("Instagram에서 place road address name 가져오기")
    @Test
    void getRoadAddressName() {
        Place place = Place.builder()
                .location(new Location(null, "인천시 가석로"))
                .build();
        Instagram instagram = Instagram.builder()
                .place(place).build();

        assertThat(instagram.getRoadAddressName()).isEqualTo("인천시 가석로");
    }

    @DisplayName("Instagram에서 place kakao id 가져오기")
    @Test
    void getKakaoId() {
        Place place = Place.builder()
                .kakaoId("1").build();
        Instagram instagram = Instagram.builder()
                .place(place).build();

        assertThat(instagram.getKakaoId()).isEqualTo("1");
    }
}