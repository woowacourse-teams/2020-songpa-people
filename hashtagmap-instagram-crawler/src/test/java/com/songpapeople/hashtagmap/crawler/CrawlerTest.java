package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CrawlerTest {
    private Crawler crawler;

    @BeforeEach
    private void setUp() {
        crawler = new Crawler();
    }

    @Disabled
    @DisplayName("크롤링 정상 동작 테스트")
    @Test
    void crawling() {
        String body = crawler.crawl("https://www.naver.com/");
        assertThat(body).isNotNull();
    }

    @DisplayName("잘못된 url 크롤링 시 예외 테스트")
    @Test
    void crawlingUrlException() {
        assertThatThrownBy(() -> crawler.crawl("https://www.asd!@#ASDQQWE1.com/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.URL_NOT_CONNECT.getMessage());
    }
}
