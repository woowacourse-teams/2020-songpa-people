package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.util.PlaceNameParser;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";

    private Crawler crawler;

    public InstagramCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    private CrawlingDto createCrawlingDto(String placeName, String body) {
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);
        String hashTagCount = instaCrawlingResult.findHashTagCount();
        PostDtos postDtos = instaCrawlingResult.findPostDtos();
        return CrawlingDto.of(placeName, hashTagCount, postDtos);
    }

    public CrawlingDto crawler(String placeName) {
        String parsedPlaceName = PlaceNameParser.parsePlaceName(placeName);
        String body = crawler.crawl(String.format(INSTAGRAM_URL_FORMAT, parsedPlaceName));
        return createCrawlingDto(parsedPlaceName, body);
    }
}
