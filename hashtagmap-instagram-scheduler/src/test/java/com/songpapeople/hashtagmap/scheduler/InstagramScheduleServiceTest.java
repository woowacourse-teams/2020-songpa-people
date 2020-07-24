package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InstagramScheduleServiceTest {
    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("db에서 모든 place 가져오는 기능 테스트")
    @Test
    void findAllPlace() {
        placeRepository.save(Place.builder()
                .placeName("스타벅스")
                .build());
        placeRepository.save(Place.builder()
                .placeName("이디야")
                .build());

        List<Place> places = placeRepository.findAll();

        assertThat(places).hasSize(2);
    }
}
