package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstagramCommandServiceTest {
    @Autowired
    private InstagramCommandService instagramCommandService;

    @Autowired
    private InstagramRepository instagramRepository;

    @DisplayName("instagram update 테스트")
    @Test
    void updateByBlackList() {
        Instagram origin = Instagram.builder()
                .hashtagCount(100000L)
                .hashtagName("origin")
                .build();
        instagramRepository.save(origin);

        instagramCommandService.update(origin, "newName",1000L);

        Instagram updatedInstagram = instagramRepository.findById(origin.getId()).get();
        assertAll(
                ()->assertThat(updatedInstagram.getId()).isEqualTo(origin.getId()),
                ()->assertThat(updatedInstagram.getHashtagName()).isEqualTo("newName"),
                ()->assertThat(updatedInstagram.getHashtagCount()).isEqualTo(1000L)
        );
    }
}
