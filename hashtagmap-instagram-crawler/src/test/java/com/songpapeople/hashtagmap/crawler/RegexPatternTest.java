package com.songpapeople.hashtagmap.crawler;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.exception.NotFoundRegexException;

class RegexPatternTest {

    @Test
    void extractHashtagCount() {
        String extracted = RegexPattern.HASH_TAG_COUNT.extract("kimchi\"edge_hashtag_to_media\":{\"count\":7777, asd}");
        assertThat(extracted).isEqualTo("7777");
    }

    @Test
    void extractHashtagPopularPostsInfo() {
        String extracted = RegexPattern.HASHTAG_POPULAR_POSTS_INFO
            .extract("\"edge_hashtag_to_top_posts\":hackseong, dundung,\"edge_hashtag_to_content_advisory\"");
        assertThat(extracted).isEqualTo("hackseong, dundung");
    }

    @Test
    void extractNotFound() {
        assertThatThrownBy(() -> RegexPattern.HASH_TAG_COUNT.extract("abcdefg"))
            .isInstanceOf(NotFoundRegexException.class);
    }
}