package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.HashtagDto;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";

    public HashtagDto createHashtagDto(String placeName) {
        Crawler.crawling(String.format(INSTAGRAM_URL_FORMAT, placeName));
        return null;
    }
}
