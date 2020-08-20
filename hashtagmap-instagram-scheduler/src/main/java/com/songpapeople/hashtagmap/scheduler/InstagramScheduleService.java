package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.Crawler;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstagramScheduleService {
    private static final int START_TRY_COUNT = 0;

    private final InstagramCrawler instagramCrawler = new InstagramCrawler(new Crawler());

    public Optional<CrawlingResult> createCrawlingResult(Place place) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
                new ProxySetter(ProxiesFactory.create()), instagramCrawler);

        return crawlerWithProxy.crawlInstagram(place, START_TRY_COUNT);
    }

    public Long findHashtagCount(String replaceName) {
        CrawlingDto crawlingDto = instagramCrawler.crawler(replaceName);
        return crawlingDto.getHashtagCount();
    }
}
