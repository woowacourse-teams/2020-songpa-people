package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramSchedulerTest {
    private InstagramScheduler instagramScheduler;

    @Mock
    private InstagramScheduleService instagramScheduleService;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @BeforeEach
    void setUp() {
        instagramScheduler = new InstagramScheduler(instagramPostRepository
                , placeRepository
                , instagramRepository
                , instagramScheduleService);
    }

    @DisplayName("db에서 place를 가져와 instagramPosts를 만들어 저장하는 기능 테스트")
    @Test
    void update() {
        Place place1 = Place.builder()
                .placeName("스타벅스")
                .build();
        placeRepository.save(place1);

        PostDtos postDtos = MockDataFactory.createPostDtos();
        CrawlingResult crawlingResult = new CrawlingResult(CrawlingDto.of(place1.getPlaceName(), String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT), postDtos), place1);

        when(instagramScheduleService.createCrawlingResult(any()))
                .thenReturn(Optional.of(crawlingResult));

        instagramScheduler.update();

        List<InstagramPost> instagramPosts = instagramPostRepository.findAll();
        assertThat(instagramPosts).hasSize(9);
    }
}
