package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CrawlerWithProxy {
    private static final int START_TRY_COUNT = 0;
    private static final int MAX_TRY_COUNT = 3;

    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;

    public CrawlerWithProxy(ProxySetter proxySetter, InstagramCrawler instagramCrawler) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
    }

    public Optional<CrawlingResult> crawlInstagram(Place place, String hashtagNameToCrawl) {
        return crawlInstagram(place, hashtagNameToCrawl, START_TRY_COUNT);
    }

    private Optional<CrawlingResult> crawlInstagram(Place place, String hashtagNameToCrawl, int tryCount) {
        if (tryCount >= MAX_TRY_COUNT) {
            return Optional.empty();
        }
        try {
            proxySetter.setProxy();
            return Optional.of(new CrawlingResult(instagramCrawler.crawler(hashtagNameToCrawl), place));
        } catch (CrawlerException e) {
            log.info("CrawlerException: {}", e.getMessage());
            if (CrawlerExceptionStatus.NOT_FOUND_URL.getStatusCode().equals(e.getErrorCode())) {
                return Optional.empty();
            }
            return crawlInstagram(place, hashtagNameToCrawl, tryCount + 1);
        } catch (InstagramSchedulerException e) {
            log.info("InstagramSchedulerException: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
