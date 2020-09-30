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
    void crawlingTest() {
        String body = crawler.crawl("https://www.naver.com/");
        assertThat(body).isNotNull();
    }

    @DisplayName("잘못된 url 크롤링 시 예외 테스트")
    @Test
    void crawlingUrlExceptionTest() {
        assertThatThrownBy(() -> crawler.crawl("https://www.asd!@#ASDQQWE1.com/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.URL_NOT_CONNECT.getMessage());
    }

    @DisplayName("찾을 수 없는 URL 예외 테스트")
    @Test
    void notFoundUrlExceptionTest() {
        assertThatThrownBy(() -> crawler.crawl("https://www.instagram.com/explore/tags/절대로_있을_리가_없는_검색어_1234/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.NOT_FOUND_URL.getMessage());
    }
}
