package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
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

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("블랙리스트 인스타그램을 대체어로 검색하여 update하는 기능 테스트")
    @Test
    void updateByBlackList() {
        Place place = Place.builder()
                .placeName("place")
                .kakaoId("100")
                .build();
        Instagram origin = Instagram.builder()
                .hashtagCount(100000L)
                .hashtagName("origin")
                .place(place)
                .build();
        placeRepository.save(place);
        instagramRepository.save(origin);

        BlackListAddRequest blackListAddRequest = new BlackListAddRequest("100","newName");
        instagramCommandService.updateByBlackList(blackListAddRequest);

        Instagram updatedInstagram = instagramRepository.findById(origin.getId()).get();
        assertThat(updatedInstagram.getHashtagName()).isEqualTo("newName");
    }
}
