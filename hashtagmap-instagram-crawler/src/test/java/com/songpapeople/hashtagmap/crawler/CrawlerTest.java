package com.songpapeople.hashtagmap.crawler;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.exception.CrawlingUrlException;

public class CrawlerTest {

    @Test
    void crawling() {
        Document document = Crawler.crawling("https://www.naver.com/");

        assertThat(document).isNotNull();
    }

    @Test
    void crawlingUrlException() {
        assertThatThrownBy(() -> Crawler.crawling("https://www.asd!@#ASDQQWE1.com/"))
            .isInstanceOf(CrawlingUrlException.class);
    }
}
