package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.SchedulerTestResource;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstagramScheduleControllerTest extends SchedulerTestResource {
    private InstagramScheduleController instagramScheduleController;

    @Mock
    private InstagramScheduleService instagramScheduleService;

    @BeforeEach
    void setUp() {
        instagramScheduleController = new InstagramScheduleController(instagramScheduleService);
    }

    @Test
    void update() {
        List<Place> places = new ArrayList<>();
        Place place1 = Place.builder()
                .placeName("스타벅스")
                .build();
        Place place2 = Place.builder()
                .placeName("이디야")
                .build();
        places.add(place1);
        places.add(place2);

        PostDtos postDtos = createPostDtos();

        List<CrawlingResult> crawlingResults = new ArrayList<>();
        crawlingResults.add(new CrawlingResult(
                CrawlingDto.of(place1.getPlaceName(), "10", postDtos), place1)
        );
        crawlingResults.add(new CrawlingResult(
                CrawlingDto.of(place2.getPlaceName(), "10", postDtos), place2)
        );

        when(instagramScheduleService.createCrawlingResult(anyList()))
                .thenReturn(crawlingResults);
        when(instagramScheduleService.findAllPlace()).thenReturn(places);

        instagramScheduleController.update();
    }
}
