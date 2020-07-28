package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

class RegexPatternTest {
    @DisplayName("정규표현식으로 해시태그 개수 추출 테스트")
    @Test
    void extractHashtagCount() {
        String extracted = RegexPattern.HASH_TAG_COUNT.extract("kimchi\"edge_hashtag_to_media\":{\"count\":7777, asd}");
        assertThat(extracted).isEqualTo("7777");
    }

    @DisplayName("정규표현식으로 인기게시물 데이터 추출 테스트")
    @Test
    void extractHashtagPopularPostsInfo() {
        String extracted = RegexPattern.HASHTAG_POPULAR_POSTS_INFO
                .extract("\"edge_hashtag_to_top_posts\":hackseong, dundung,\"edge_hashtag_to_content_advisory\"");
        assertThat(extracted).isEqualTo("hackseong, dundung");
    }

    @DisplayName("데이터 추출에 실패한 경우 예외 테스트")
    @Test
    void extractNotFound() {
        assertThatThrownBy(() -> RegexPattern.HASH_TAG_COUNT.extract("abcdefg"))
                .isInstanceOf(CrawlerException.class)
                .hasMessage(CrawlerExceptionStatus.NOT_FOUND_MATCH_REGEX.getMessage());
    }
}
