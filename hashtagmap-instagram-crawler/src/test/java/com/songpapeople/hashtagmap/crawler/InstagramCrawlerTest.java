package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class InstagramCrawlerTest {
    @Test
    void createHashtagDto() {
        String searchkey = "스타벅스";

        InstagramCrawler instagramCrawler = new InstagramCrawler();
        CrawlingDto crawlingDto = instagramCrawler.createHashtagDto(searchkey);

        assertAll(
                () -> assertThat(crawlingDto.getPlaceName()).isEqualTo(searchkey),
                () -> assertThat(crawlingDto.getHashtagCount()).isNotNull(),
                () -> assertThat(crawlingDto.getPostDtos().size()).isEqualTo(9)
        );
    }
}
