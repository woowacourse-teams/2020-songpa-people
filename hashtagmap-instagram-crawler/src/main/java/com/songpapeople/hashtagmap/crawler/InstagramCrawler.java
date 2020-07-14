package com.songpapeople.hashtagmap.crawler;

import org.jsoup.nodes.Document;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.songpapeople.hashtagmap.HashtagDto;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";
    private static final String HASHTAG_COUNT_REGEX = "(\"edge_hashtag_to_media\":\\{\"count\"):([0-9]+)";
    private static final String HASHTAG_POPULAR_INFO_REGEX = "(\"edge_hashtag_to_top_posts\":)(.*)(,\"edge_hashtag_to_content_advisory\")";

    public HashtagDto createHashtagDto(String placeName) {
        Document doc = Crawler.crawling(String.format(INSTAGRAM_URL_FORMAT, placeName));
        String body = doc.body().toString();
        String hashtagCount = CrawlingProcessor.searchByRegex(body, HASHTAG_COUNT_REGEX);
        String popularInfo = CrawlingProcessor.searchByRegex(body, HASHTAG_POPULAR_INFO_REGEX);
        JsonElement json = JsonParser.parseString(popularInfo);

        return null;
    }
}
