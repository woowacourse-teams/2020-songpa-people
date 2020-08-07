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

    // TODO: 2020/07/24 crawler클래스에서 검색결과는 없는 것 예외처리 테스트
    @DisplayName("검색할 수 없는 Place 검색시 예외 처리 테스트")
    @Test
    void instagramCrawling() {
        Place place = Place.builder()
                .placeName("잠실타로&사주")
                .build();

        when(instagramCrawler.crawler(place.getPlaceName()))
                .thenThrow(InstagramSchedulerException.class);

        assertThatThrownBy(() -> crawlerWithProxy.crawlInstagram(place, 0))
                .isInstanceOf(InstagramSchedulerException.class);
    }
}
