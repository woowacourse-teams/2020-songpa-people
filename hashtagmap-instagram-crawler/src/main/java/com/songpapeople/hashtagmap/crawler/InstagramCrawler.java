package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";
    private static final int FIRST_INDEX = 0;

    public CrawlingDto createCrawlingDto(String placeName, String body) {
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);
        String hashTagCount = instaCrawlingResult.findHashTagCount();
        PostDtos postDtos = instaCrawlingResult.findPostDtos();
        return CrawlingDto.of(placeName, hashTagCount, postDtos);
    }

    public CrawlingDto crawler(String placeName) {
        String body = Crawler.crawl(String.format(INSTAGRAM_URL_FORMAT, parsePlaceName(placeName)));
        return createCrawlingDto(placeName, body);
    }

    private String parsePlaceName(String placeName) {
        String parsedPlaceName = placeName.replaceAll(" ", "");
        if (parsedPlaceName.endsWith("점")) {
            return parsedPlaceName.substring(FIRST_INDEX, parsedPlaceName.length() - 1);
        }
        return parsedPlaceName;
    }
}
