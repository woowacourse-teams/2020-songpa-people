package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CrawlerWithProxy {
    private static final int MAX_TRY_COUNT = 3;

    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;
    private final InstagramScheduleService instagramScheduleService;

    public CrawlerWithProxy(ProxySetter proxySetter
            , InstagramCrawler instagramCrawler
            , InstagramScheduleService instagramScheduleService) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
        this.instagramScheduleService = instagramScheduleService;
    }

    public Optional<CrawlingResult> crawlInstagram(Place place, int tryCount) {
        if (tryCount > MAX_TRY_COUNT || instagramScheduleService.isSkipPlace(place)) {
            return Optional.empty();
        }
        try {
            proxySetter.setProxy();
            String hashtagNameToCraw = instagramScheduleService.findHashtagNameToCrawl(place);
            return Optional.of(new CrawlingResult(instagramCrawler.crawler(hashtagNameToCraw), place));
        } catch (CrawlerException e) {
            log.info("CrawlerException: {}", e.getMessage());
            return crawlInstagram(place, tryCount + 1);
        } catch (InstagramSchedulerException e) {
            log.info("InstagramSchedulerException: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
