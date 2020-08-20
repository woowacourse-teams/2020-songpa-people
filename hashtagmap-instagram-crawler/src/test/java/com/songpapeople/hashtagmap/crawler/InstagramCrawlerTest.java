package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstagramCrawlerTest {
    @Mock
    private Crawler crawler;

    @DisplayName("인스타그램 크롤링 테스트")
    @ParameterizedTest
    @CsvSource({"스타벅스,스타벅스", "스타 벅스,스타벅스", "스타벅스 강남점,스타벅스강남"})
    void createCrawlingDtoTest(String placeName, String parsedPlaceName) {
        when(crawler.crawl(any())).thenReturn(MockDataFactory.createBody());
        InstagramCrawler instagramCrawler = new InstagramCrawler(crawler);
        CrawlingDto crawlingDto = instagramCrawler.crawler(placeName);

        assertAll(
                () -> assertThat(crawlingDto.getPlaceName()).isEqualTo(parsedPlaceName),
                () -> assertThat(crawlingDto.getHashtagCount()).isNotNull(),
                () -> assertThat(crawlingDto.getPostDtos().size()).isEqualTo(9)
        );
    }
}
