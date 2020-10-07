package com.songpapeople.hashtagmap.instagram.domain.model.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InstagramForUpdateTest {


    @DisplayName("InstagramForUpdate -> Instagram 엔티티로 바꾸기")
    @Test
    void toInstagram() {
        Long instagramId = 1L;
        Long placeId = 2L;
        InstagramForUpdate instagramForUpdate = new InstagramForUpdate(instagramId, placeId);

        Instagram instagram = instagramForUpdate.toInstagram();

        assertAll(() -> {
            assertThat(instagram.getId()).isEqualTo(instagramId);
            assertThat(instagram.getPlace().getId()).isEqualTo(placeId);
        });
    }
}