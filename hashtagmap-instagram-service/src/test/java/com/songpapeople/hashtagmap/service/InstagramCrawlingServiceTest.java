package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramCrawlingServiceTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private InstagramCrawlingService instagramCrawlingService;

    @MockBean
    private InstagramCrawler instagramCrawler;


    @DisplayName("Skip하는 가게라면 크롤링하지 않고 빈 결과를 반환한다.")
    @Test
    void isSkipTest() {
        Place place = placeRepository.save(
                Place.builder()
                        .kakaoId("12345")
                        .build()
        );
        blackListRepository.save(new BlackList(place.getKakaoId(), "newName", true));

        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);
        assertThat(crawlingResult.isPresent()).isFalse();
    }

    @DisplayName("blackList에 등록되지 않은 경우 검색어 테스트")
    @Test
    void findHashtagNameToCrawlTest() {
        // given
        final String PLACE_NAME = "스타벅스잠실";

        Place place = placeRepository.save(
                Place.builder()
                        .placeName(PLACE_NAME)
                        .kakaoId("12345")
                        .build()
        );

        CrawlingDto crawlingDto = CrawlingDto.of(PLACE_NAME, String.valueOf(1000), null);
        when(instagramCrawler.crawler(PLACE_NAME)).thenReturn(crawlingDto);

        // when
        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);

        // then
        String hashtagName = crawlingResult.get().createInstagram().getHashtagName();
        assertThat(hashtagName).isEqualTo(PLACE_NAME);
    }

    @DisplayName("blackList에 등록된 경우 검색어 테스트")
    @Test
    void findBlackListHashtagNameToCrawlTest() {
        // given
        final String PLACE_NAME = "블루보틀성수역점";
        final String NEW_HASHTAG_NAME = "블루보틀";

        Place place = placeRepository.save(
                Place.builder()
                        .placeName(PLACE_NAME)
                        .kakaoId("12345")
                        .build()
        );
        blackListRepository.save(new BlackList(place.getKakaoId(), NEW_HASHTAG_NAME));

        CrawlingDto crawlingDto = CrawlingDto.of(NEW_HASHTAG_NAME, String.valueOf(1000), null);
        when(instagramCrawler.crawler(NEW_HASHTAG_NAME)).thenReturn(crawlingDto);

        // when
        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);

        // then
        String hashtagName = crawlingResult.get().createInstagram().getHashtagName();
        assertThat(hashtagName).isEqualTo(NEW_HASHTAG_NAME);
    }


    @AfterEach
    private void tearDown() {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
        blackListRepository.deleteAll();
    }
}
