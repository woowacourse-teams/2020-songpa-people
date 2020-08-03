package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InstagramCrawlerTest {

    @DisplayName("인스타그램 크롤링 테스트")
    @Test
    void createHashtagDto() {
        String searchKey = "스타벅스";

        InstagramCrawler instagramCrawler = new InstagramCrawler();
        CrawlingDto crawlingDto = instagramCrawler.createCrawlingDto(searchKey, MockDataFactory.createBody());

        assertAll(
                () -> assertThat(crawlingDto.getPlaceName()).isEqualTo(searchKey),
                () -> assertThat(crawlingDto.getHashtagCount()).isNotNull(),
                () -> assertThat(crawlingDto.getPostDtos().size()).isEqualTo(9)
        );
    }

    @DisplayName("가게 이름 데이터 가공하기")
    @ParameterizedTest
    @CsvSource({"스타벅스 강남역점,스타벅스강남역", "피자나라 치킨공주,피자나라치킨공주"})
    void parsePlaceName(String placeName, String expected) {
        InstagramCrawler instagramCrawler = new InstagramCrawler();
        String actual = instagramCrawler.parsePlaceName(placeName);
        assertThat(actual).isEqualTo(expected);
    }
}
