package com.songpapeople.hashtagmap.scheduler;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;

@SpringBootTest
class InstagramScheduleServiceTest {
    @Autowired
    private InstagramScheduleService instagramScheduleService;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("db에서 모든 instagramPost 가져오는 기능 테스트")
    @Test
    void saveAllInstagramPosts() {
        List<InstagramPost> instagramPosts = new ArrayList<>();
        Place place = Place.builder()
                .placeName("스타벅스")
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagName("스타벅스")
            .hashtagCount(100L)
                .build();
        instagramPosts.add(InstagramPost.builder()
                .postUrl("postUrl1")
                .imageUrl("imageUrl1")
                .instagram(instagram)
                .build());
        instagramPosts.add(InstagramPost.builder()
                .postUrl("postUrl2")
                .imageUrl("imageUrl2")
                .instagram(instagram)
                .build());

        instagramScheduleService.saveAllInstagramPosts(instagramPosts);

        assertThat(instagramPostRepository.findAll()).hasSize(2);
    }

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
