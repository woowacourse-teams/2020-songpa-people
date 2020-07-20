package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlingUrlException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class CrawlerTest {
    @DisplayName("크롤링 정상 동작 테스트")
    @Test
    void crawling() {
        Document document = Crawler.crawling("https://www.naver.com/");

        assertThat(document).isNotNull();
    }

    @DisplayName("잘못된 url 크롤링 시 예외 테스트")
    @Test
    void crawlingUrlException() {
        assertThatThrownBy(() -> Crawler.crawling("https://www.asd!@#ASDQQWE1.com/"))
                .isInstanceOf(CrawlingUrlException.class);
    }
}
