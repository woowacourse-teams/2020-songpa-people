package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InstagramRepositoryTest {
    private static final int SIZE = 10;
    private static final int MIN_HASHTAG_COUNT = 100;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @DisplayName("정렬된 hashtagCount를 N개의 그룹으로 나눈 후, 각 그룹의 최소/최대 hashtagCount를 확인한다.")
    @Test
    public void InstagramRepositoryTest() {
        // given
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            places.add(Place.builder().build());
        }

        List<Long> hashtagCounts = LongStream.range(MIN_HASHTAG_COUNT, MIN_HASHTAG_COUNT + SIZE)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(hashtagCounts);

        List<Instagram> instagrams = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            instagrams.add(Instagram.builder()
                    .place(places.get(i))
                    .hashtagCount(hashtagCounts.get(i))
                    .build());
        }

        placeRepository.saveAll(places);
        instagramRepository.saveAll(instagrams);

        // when
        int tileSize = 2;
        List<List<Long>> actual = instagramRepository.findTiledHashtagCount(tileSize);

        // then
        Collections.sort(hashtagCounts);
        assertThat(actual.get(0).get(0)).isEqualTo(hashtagCounts.get(0));
        assertThat(actual.get(0).get(1)).isEqualTo(hashtagCounts.get(SIZE / tileSize - 1));
        assertThat(actual.get(1).get(0)).isEqualTo(hashtagCounts.get(SIZE / tileSize));
        assertThat(actual.get(1).get(1)).isEqualTo(hashtagCounts.get(SIZE - 1));
    }
}