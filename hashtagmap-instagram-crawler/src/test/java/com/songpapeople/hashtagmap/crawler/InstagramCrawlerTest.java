package com.songpapeople.hashtagmap.crawler;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.MockDataReader;
import com.songpapeople.hashtagmap.dto.CrawlingDto;

class InstagramCrawlerTest extends MockDataReader {

    @DisplayName("인스타그램 크롤링 테스트")
    @Test
    void createHashtagDto() {
        String searchKey = "스타벅스";

        InstagramCrawler instagramCrawler = new InstagramCrawler();
        CrawlingDto crawlingDto = instagramCrawler.createCrawlingDto(searchKey, createBody());

        assertAll(
            () -> assertThat(crawlingDto.getPlaceName()).isEqualTo(searchKey),
                () -> assertThat(crawlingDto.getHashtagCount()).isNotNull(),
                () -> assertThat(crawlingDto.getPostDtos().size()).isEqualTo(9)
        );
    }

}
