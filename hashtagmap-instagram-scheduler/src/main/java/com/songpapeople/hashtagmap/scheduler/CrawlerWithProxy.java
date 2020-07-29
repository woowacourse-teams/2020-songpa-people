package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.Optional;

public class CrawlerWithProxy {
    private static final int MAX_TRY_COUNT = 3;

    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;

    public CrawlerWithProxy(ProxySetter proxySetter, InstagramCrawler instagramCrawler) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
    }

    public Optional<CrawlingResult> crawlInstagram(Place place, int tryCount) {
        if (tryCount > MAX_TRY_COUNT) {
            return Optional.empty();
        }
        try {
            proxySetter.setProxy();
            return Optional.of(new CrawlingResult(instagramCrawler.crawler(place.getPlaceName()), place));
        } catch (CrawlerException e) {
            return crawlInstagram(place, tryCount + 1);
        }
    }
}
