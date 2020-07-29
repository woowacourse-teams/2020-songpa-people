package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";

    public CrawlingDto createCrawlingDto(String placeName, String body) {
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);
        String hashTagCount = instaCrawlingResult.findHashTagCount();
        PostDtos postDtos = instaCrawlingResult.findPostDtos();
        return CrawlingDto.of(placeName, hashTagCount, postDtos);
    }

    public CrawlingDto crawler(String placeName) {
        String body = Crawler.crawl(String.format(INSTAGRAM_URL_FORMAT, placeName));
        return createCrawlingDto(placeName, body);
    }
}
