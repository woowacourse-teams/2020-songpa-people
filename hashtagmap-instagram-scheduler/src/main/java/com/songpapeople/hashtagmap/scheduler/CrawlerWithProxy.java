package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlingUrlException;
import com.songpapeople.hashtagmap.place.domain.model.Place;

public class CrawlerWithProxy {
    private final ProxySetter proxySetter;
    private final InstagramCrawler instagramCrawler;

    public CrawlerWithProxy(ProxySetter proxySetter, InstagramCrawler instagramCrawler) {
        this.proxySetter = proxySetter;
        this.instagramCrawler = instagramCrawler;
    }

    public CrawlingResult instagramCrawling(Place place, int tryCount) {
        if (tryCount > 3) {
            throw new IllegalArgumentException(place.getPlaceName() + " 검색할 수 없음");
        }
        try {
            proxySetter.set();
            return new CrawlingResult(instagramCrawler.crawling(place.getPlaceName()), place);
        } catch (CrawlingUrlException e) {
            System.out.println(e.getMessage());
            return instagramCrawling(place, tryCount + 1);
        }
    }
}
