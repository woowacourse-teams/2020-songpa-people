package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.songpapeople.hashtagmap.HashtagDto;
import org.jsoup.nodes.Document;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";

    public HashtagDto createHashtagDto(String placeName) {
        Document doc = Crawler.crawling(String.format(INSTAGRAM_URL_FORMAT, placeName));
        String body = doc.body().toString();
        String hashtagCount = CrawlingProcessor.findHashtagCount(body);
        String popularInfo = CrawlingProcessor.slicePopularInfo(body);

        JsonElement json = JsonParser.parseString(popularInfo);

        return null;
    }
}
