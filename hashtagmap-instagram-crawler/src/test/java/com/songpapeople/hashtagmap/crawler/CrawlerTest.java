package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrawlerTest {
    private Crawler crawler;

    @BeforeEach
    private void setUp() {
        crawler = new Crawler();
    }

    @Disabled
    @DisplayName("크롤링 정상 동작 테스트")
    @Order(1)
    @Test
    void crawlingTest() {
        String body = crawler.crawl("https://www.naver.com/");
        assertThat(body).isNotNull();
    }

    @DisplayName("잘못된 url 크롤링 시 예외 테스트")
    @Order(2)
    @Test
    void crawlingUrlExceptionTest() {
        assertThatThrownBy(() -> crawler.crawl("https://www.asd!@#ASDQQWE1.com/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.URL_NOT_CONNECT.getMessage());
    }

    @DisplayName("찾을 수 없는 URL 예외 테스트")
    @Test
    @Order(3)
    void notFoundUrlExceptionTest() {
        assertThatThrownBy(() -> crawler.crawl("https://www.instagram.com/explore/tags/절대로_있을_리가_없는_검색어_1234/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.NOT_FOUND_URL.getMessage());
    }

    @DisplayName("너무 많은 요청을 보내 발생하는 429 예외 테스트")
    @Test
    @Order(4)
    void tooManyRequestExceptionTest() {
        List<Integer> numbers = IntStream.range(0, 200).boxed().collect(Collectors.toList());
        try {
            numbers.parallelStream()
                    .map(num -> crawler.crawl("https://www.instagram.com/explore/tags/" + num + "/")).count();
        } catch (CrawlerException e) {

        }

        assertThatThrownBy(() -> crawler.crawl("https://www.instagram.com/explore/tags/daily/"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.TOO_MANY_REQUEST.getMessage());
    }
}
