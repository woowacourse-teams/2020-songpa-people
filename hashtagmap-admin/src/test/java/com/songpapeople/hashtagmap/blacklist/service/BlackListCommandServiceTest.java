package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class BlackListCommandServiceTest {
    @Autowired
    private BlackListCommandService blackListCommandService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @DisplayName("잘못된 검색어로 검색된 가게 삭제후 블랙리스트 등록 기능 테스트")
    @Test
    void deleteInstagramAfterAddBlackList() {
        Place place = Place.builder()
                .placeName("place")
                .location(new Location(new Point("40", "130"), "address"))
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(100L)
                .hashtagName(place.getPlaceName())
                .build();
        placeRepository.save(place);
        instagramRepository.save(instagram);

        BlackListRequest blackListRequest = new BlackListRequest(place.getId(), "");
        blackListCommandService.deleteInstagramAfterAddBlackList(blackListRequest);

        assertAll(
                () -> assertThat(blackListRepository.findByPlaceId(place.getId()).isPresent()).isTrue(),
                () -> assertThat(instagramRepository.findById(instagram.getId()).isPresent()).isFalse()
        );
    }
}
