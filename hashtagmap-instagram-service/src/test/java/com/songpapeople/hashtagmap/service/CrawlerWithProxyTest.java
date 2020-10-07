package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

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

    @DisplayName("429 예외가 발생하면 예외를 반환한다.")
    @Test
    void tooManyRequestExceptionTest() {
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();
        CrawlerException exception = new CrawlerException(CrawlerExceptionStatus.TOO_MANY_REQUEST);
        when(instagramCrawler.crawler(place.getPlaceName())).thenThrow(exception);

        assertThatThrownBy(() -> crawlerWithProxy.crawlInstagram(place, place.getPlaceName()))
                .isInstanceOf(exception.getClass())
                .hasMessage(exception.getMessage());
    }

    @DisplayName("404 예외가 발생하면 빈 결과를 반환한다.")
    @Test
    void notFoundPlaceExceptionTest() {
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();
        CrawlerException exception = new CrawlerException(CrawlerExceptionStatus.NOT_FOUND_URL);
        when(instagramCrawler.crawler(place.getPlaceName())).thenThrow(exception);

        // when
        Optional<CrawlingResult> result = crawlerWithProxy.crawlInstagram(place, place.getPlaceName());

        // then
        assertThat(result.isPresent()).isFalse();
    }
}
