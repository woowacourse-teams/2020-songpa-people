package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.SchedulerTestResource;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramSchedulerTest extends SchedulerTestResource {
    private InstagramScheduler instagramScheduler;
    private Place place1;
    private Place place2;

    @Mock
    private InstagramScheduleService instagramScheduleService;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        instagramScheduler = new InstagramScheduler(instagramPostRepository, placeRepository, instagramScheduleService);
        place1 = Place.builder()
                .placeName("스타벅스")
                .build();
        place2 = Place.builder()
                .placeName("이디야")
                .build();
    }

    @Test
    void update() {
        List<Place> places = Arrays.asList(place1, place2);
        placeRepository.saveAll(places);
        PostDtos postDtos = createPostDtos();

        List<CrawlingResult> crawlingResults = Arrays.asList(
                new CrawlingResult(CrawlingDto.of(place1.getPlaceName(), "100", postDtos), place1),
                new CrawlingResult(CrawlingDto.of(place2.getPlaceName(), "100", postDtos), place2)
        );

        when(instagramScheduleService.createCrawlingResult(anyList()))
                .thenReturn(crawlingResults);

        instagramScheduler.update();

        List<InstagramPost> instagramPosts = instagramPostRepository.findAll();
        assertThat(instagramPosts).hasSize(18);
    }
}
