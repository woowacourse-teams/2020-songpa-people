package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CrawlerWithProxy {
    private static final String NOT_FOUND_EXCEPTION_CODE = CrawlerExceptionStatus.NOT_FOUND_URL.getStatusCode();

    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;

    public CrawlerWithProxy(ProxySetter proxySetter, InstagramCrawler instagramCrawler) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
    }

    public Optional<CrawlingResult> crawlInstagram(Place place, String hashtagNameToCrawl) {
        try {
            proxySetter.setProxy();
            return Optional.of(new CrawlingResult(instagramCrawler.crawler(hashtagNameToCrawl), place));
        } catch (CrawlerException e) {
            log.info("CrawlerException: {}", e.getMessage());
            if (NOT_FOUND_EXCEPTION_CODE.equals(e.getErrorCode())) {
                return Optional.empty();
            }
            throw e;
        }
    }
}
