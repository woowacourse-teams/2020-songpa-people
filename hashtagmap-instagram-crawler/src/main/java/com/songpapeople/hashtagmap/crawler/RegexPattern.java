package com.songpapeople.hashtagmap.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.songpapeople.hashtagmap.exception.NotFoundRegexException;
import lombok.Getter;

@Getter
public enum RegexPattern {
    HASH_TAG_COUNT(Pattern.compile("(\"edge_hashtag_to_media\":\\{\"count\"):([0-9]+)")),
    HASHTAG_POPULAR_POSTS_INFO(Pattern.compile("(\"edge_hashtag_to_top_posts\":)(.*)(,\"edge_hashtag_to_content_advisory\")"));

    private Pattern pattern;

    RegexPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String extract(String body) {
        Matcher matcher = this.pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(2);
        }
        throw new NotFoundRegexException();
    }
}
