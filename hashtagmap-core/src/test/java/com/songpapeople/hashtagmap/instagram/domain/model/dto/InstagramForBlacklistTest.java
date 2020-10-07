package com.songpapeople.hashtagmap.instagram.domain.model.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InstagramForBlacklistTest {

    @DisplayName("InstagramForBlacklist -> Instagram 엔티티로 바꾸기")
    @Test
    void toInstagram() {
        Long hashtagCount = 100L;
        String hashtagName = "스타벅스홍대입구";
        String kakaoId = "777";
        String placeName = "스타벅스 홍대입구점";
        String roadAddressName = "서울시 강남구";

        InstagramForBlacklist instagramForBlacklist = InstagramForBlacklist.builder()
                .hashtagCount(hashtagCount)
                .hashtagName(hashtagName)
                .kakaoId(kakaoId)
                .placeName(placeName)
                .roadAddressName(roadAddressName)
                .build();


        Instagram actual = instagramForBlacklist.toInstagram();

        assertAll(() -> {
            assertThat(actual.getHashtagCount()).isEqualTo(hashtagCount);
            assertThat(actual.getHashtagName()).isEqualTo(hashtagName);
            assertThat(actual.getKakaoId()).isEqualTo(kakaoId);
            assertThat(actual.getRoadAddressName()).isEqualTo(roadAddressName);
            assertThat(actual.getPlaceName()).isEqualTo(placeName);
        });
    }
}