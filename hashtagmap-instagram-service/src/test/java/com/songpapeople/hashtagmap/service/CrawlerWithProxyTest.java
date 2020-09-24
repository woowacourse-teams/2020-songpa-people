package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrawlerWithProxyTest {
    private CrawlerWithProxy crawlerWithProxy;

    @Mock
    private InstagramCrawler instagramCrawler;

    @Mock
    private ProxySetter proxySetter;

    @BeforeEach
    void setUp() {
        crawlerWithProxy = new CrawlerWithProxy(proxySetter, instagramCrawler);
    }

    @DisplayName("검색할 수 없는 Place 검색시 Optional.empty()반환")
    @Test
    void instagramCrawling() {
        // given
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();
        when(instagramCrawler.crawler(place.getPlaceName())).thenThrow(InstagramSchedulerException.class);

        // when
        Optional<CrawlingResult> actual = crawlerWithProxy.crawlInstagram(place, place.getPlaceName());

        // then
        assertThat(actual).isEqualTo(Optional.empty());
    }

    @DisplayName("크롤링 반복 횟수 확인 - CrawlerException 발생 시 3회 실행")
    @Test
    void crawlerExceptionTimesTest() {
        // given
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();
        when(instagramCrawler.crawler(place.getPlaceName())).thenThrow(CrawlerException.class);

        // when
        crawlerWithProxy.crawlInstagram(place, place.getPlaceName());

        // then
        verify(instagramCrawler, times(3)).crawler(place.getPlaceName());
    }


    @DisplayName("크롤링 반복 횟수 확인 - InstagramSchedulerException 발생 시 1회 실행")
    @Test
    void instagramSchedulerExceptionTimesTest() {
        // given
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();

        final int NOT_ENOUGH_HASHTAG_COUNT = 100;
        CrawlingDto crawlingDto = CrawlingDto.of(place.getPlaceName(), String.valueOf(NOT_ENOUGH_HASHTAG_COUNT), null);

        when(instagramCrawler.crawler(anyString())).thenReturn(crawlingDto);

        // when
        crawlerWithProxy.crawlInstagram(place, place.getPlaceName());

        // then
        verify(instagramCrawler, times(1)).crawler(place.getPlaceName());
    }
}
