package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstagramScheduleService {
    private static final int START_TRY_COUNT = 0;

    private final InstagramCrawler instagramCrawler = new InstagramCrawler();

    public List<CrawlingResult> createCrawlingResult(List<Place> places) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
                new ProxySetter(ProxiesFactory.create()), instagramCrawler);

        return places.stream()
                .map(place -> crawlerWithProxy.crawlInstagram(place, START_TRY_COUNT))
                .filter(Optional::isPresent)
                .map(optional -> optional.orElseThrow(NullPointerException::new))
                .filter(CrawlingResult::isOverMinHashtagCount)
                .collect(Collectors.toList());
    }
}
