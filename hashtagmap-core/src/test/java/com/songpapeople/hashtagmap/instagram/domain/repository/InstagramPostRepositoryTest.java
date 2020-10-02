package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InstagramPostRepositoryTest {
    private Place place;
    private Instagram instagram;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
    }

    @Transactional
    @DisplayName("인스타그램 아이디로 인스타그램 post들을 가져오는 기능 테스트")
    @Test
    void findAllByInstagramId() {
        place = placeRepository.save(Place.builder().build());
        instagram = Instagram.builder()
                .place(place)
                .build();
        List<InstagramPost> instagramPosts = IntStream.rangeClosed(1, 9).boxed()
                .map(this::createInstagramPost)
                .collect(Collectors.toList());
        instagram = instagramRepository.save(instagram);
        instagramPostRepository.saveAll(instagramPosts);

        System.out.println(instagram.getId());
        assertThat(instagramRepository.withPostFetch(Math.toIntExact(instagram.getId()))).hasSize(9);
    }

    private InstagramPost createInstagramPost(Integer number) {
        String url = String.valueOf(number);
        return InstagramPost.builder()
                .instagram(instagram)
                .imageUrl(url)
                .postUrl(url)
                .build();
    }

    @AfterEach
    void tearDown() {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
