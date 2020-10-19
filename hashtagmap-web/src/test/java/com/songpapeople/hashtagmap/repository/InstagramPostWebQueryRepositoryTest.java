package com.songpapeople.hashtagmap.repository;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InstagramPostWebQueryRepositoryTest {
    private Place place;
    private Instagram instagram;

    @Autowired
    private InstagramPostWebQueryRepository instagramPostWebQueryRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        place = placeRepository.save(Place.builder().build());
        instagram = instagramRepository.save(Instagram.builder()
                .place(place)
                .build());
    }

    @DisplayName("인스타그램 아이디로 instagram API에 필요한 DTO를 잘 가져오는지 테스트")
    @Test
    void findAllByInstagramId() {
        List<InstagramPost> instagramPosts = IntStream.rangeClosed(1, 9).boxed()
                .map(this::createInstagramPost)
                .collect(Collectors.toList());
        instagramPostRepository.saveAll(instagramPosts);

        List<InstagramPostResponse> instagramPostResponses = instagramPostWebQueryRepository.findAllByInstagramId(instagram.getId());
        assertThat(instagramPostResponses.size()).isEqualTo(9);
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