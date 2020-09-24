package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.util.PlaceNameParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        instagramScheduler = new InstagramScheduler(instagramScheduleService
                , instagramRepository
                , instagramPostRepository
                , placeRepository);
    }

    @DisplayName("db에서 place를 가져와 instagramPosts를 만들어 저장하는 기능 테스트")
    @Test
    void update() {
        Place starbucks = Place.builder()
                .placeName("스타벅스")
                .build();
        Place starbucksWithSpace = Place.builder()
                .placeName("스타 벅스")
                .build();
        Place starbucksWithBranch = Place.builder()
                .placeName("스타벅스 강남점")
                .build();
        placeRepository.saveAll(Arrays.asList(starbucks, starbucksWithSpace, starbucksWithBranch));

        PostDtos postDtos = MockDataFactory.createPostDtos();
        CrawlingResult crawlingResultWithStarbucks = new CrawlingResult(CrawlingDto.of(
                starbucks.getPlaceName(),
                String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT), postDtos),
                starbucks);
        CrawlingResult crawlingResultWithStarbucksWithSpace = new CrawlingResult(CrawlingDto.of(
                PlaceNameParser.parsePlaceName(starbucksWithSpace.getPlaceName()),
                String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT), postDtos),
                starbucksWithSpace);
        CrawlingResult crawlingResultWithStarbucksWithBranch = new CrawlingResult(CrawlingDto.of(
                PlaceNameParser.parsePlaceName(starbucksWithBranch.getPlaceName()),
                String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT), postDtos),
                starbucksWithBranch);

        Mockito.when(instagramScheduleService.createCrawlingResult(starbucks))
                .thenReturn(Optional.of(crawlingResultWithStarbucks));
        Mockito.when(instagramScheduleService.createCrawlingResult(starbucksWithSpace))
                .thenReturn(Optional.of(crawlingResultWithStarbucksWithSpace));
        Mockito.when(instagramScheduleService.createCrawlingResult(starbucksWithBranch))
                .thenReturn(Optional.of(crawlingResultWithStarbucksWithBranch));

        instagramScheduler.update();

        List<Instagram> instagrams = instagramRepository.findAll();
        List<InstagramPost> instagramPosts = instagramPostRepository.findAll();
        assertAll(
                () -> assertThat(instagrams).hasSize(3),
                () -> assertThat(instagrams.get(0).getHashtagName()).isEqualTo("스타벅스"),
                () -> assertThat(instagrams.get(1).getHashtagName()).isEqualTo("스타벅스"),
                () -> assertThat(instagrams.get(2).getHashtagName()).isEqualTo("스타벅스강남"),
                () -> assertThat(instagramPosts).hasSize(27)
        );
    }

    @AfterEach
    void tearDown() {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
