package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CrawlerWithProxy {

    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;

    public CrawlerWithProxy(ProxySetter proxySetter,
                            InstagramCrawler instagramCrawler) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
    }

    public Optional<CrawlingResult> crawlInstagram(Place place, String hashtagNameToCrawl) {
//        try {
//            Thread.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            return Optional.of(new CrawlingResult(instagramCrawler.crawler(hashtagNameToCrawl), place));
        } catch (CrawlerException e) {
            if (e.getErrorCode().equals("CRAWLER_1000")) {
                proxySetter.setProxy();
            }
            log.info("CrawlerException: {}", e.getMessage() + hashtagNameToCrawl);
            return Optional.empty();
        } catch (InstagramSchedulerException e) {
            log.info("InstagramSchedulerException: {}", e.getMessage() + hashtagNameToCrawl);
            return Optional.empty();
        }
    }
}
