package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InstagramForMarkerTest {

    @DisplayName("InstagramForMaker -> Instagram 엔티티로 바꾸기")
    @Test
    void toInstagram() {
        Long id = 1L;
        Long hashtagCount = 100L;
        String hashtagName = "스타벅스홍대입구";
        String placeName = "스타벅스 홍대입구점";
        String placeUrl = "www.";
        String kakaoId = "777";
        Location location = new Location(null, "서울시 강남구");
        Category category = Category.CAFE;
        InstagramForMarker instagramForMarker = InstagramForMarker.builder()
                .id(id)
                .hashtagCount(hashtagCount)
                .hashtagName(hashtagName)
                .placeName(placeName)
                .placeUrl(placeUrl)
                .kakaoId(kakaoId)
                .location(location)
                .category(category)
                .build();

        Instagram instagram = instagramForMarker.toInstagram();

        assertAll(() -> {
            assertThat(instagram.getId()).isEqualTo(id);
            assertThat(instagram.getHashtagCount()).isEqualTo(hashtagCount);
            assertThat(instagram.getHashtagName()).isEqualTo(hashtagName);
            assertThat(instagram.getPlace().getPlaceUrl()).isEqualTo(placeUrl);
            assertThat(instagram.getKakaoId()).isEqualTo(kakaoId);
            assertThat(instagram.getPlace().getLocation()).isEqualToComparingFieldByField(location);
            assertThat(instagram.getPlace().getCategory()).isEqualTo(category);
        });
    }
}