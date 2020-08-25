package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

    @DisplayName("검색할 수 없는 Place 검색시 Optional.empty()반환")
    @Test
    void instagramCrawling() {
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();

        when(instagramCrawler.crawler(place.getPlaceName()))
                .thenThrow(InstagramSchedulerException.class);

        assertThat(crawlerWithProxy.crawlInstagram(place, place.getPlaceName(), 0)).isEqualTo(Optional.empty());
    }
}
