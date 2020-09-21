package com.songpapeople.hashtagmap.instagram.domain.model;

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
}