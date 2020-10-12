package com.songpapeople.hashtagmap.instagram.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.repository.dto.InstagramBatchDto;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InstagramBatchQueryRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramBatchQueryRepository instagramBatchQueryRepository;

    @DisplayName("Instagram Batch write 작업에 필요한 Dto 조회하기")
    @Test
    void findAll() {
        Place place = Place.builder()
                .build();
        placeRepository.save(place);
        Instagram instagram = Instagram.builder()
                .place(place)
                .build();
        instagramRepository.save(instagram);

        List<InstagramBatchDto> actual = instagramBatchQueryRepository.findAll();
        InstagramBatchDto expect = new InstagramBatchDto(instagram.getId(), place.getId());

        assertThat(actual.get(0)).isEqualToComparingFieldByField(expect);
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}