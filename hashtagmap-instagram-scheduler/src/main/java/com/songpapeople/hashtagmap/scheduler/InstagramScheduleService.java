package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstagramScheduleService {
    private static final int START_TRY_COUNT = 0;

    public List<CrawlingResult> createCrawlingResult(List<Place> places) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
                new ProxySetter(ProxiesFactory.create()), new InstagramCrawler());

        return places.stream()
                .map(place -> createCrawlingResult(place, crawlerWithProxy))
                .filter(Optional::isPresent)
                .map(crawlingResult -> crawlingResult.orElseThrow(NullPointerException::new))
                .collect(Collectors.toList());
    }

    private Optional<CrawlingResult> createCrawlingResult(Place place, CrawlerWithProxy crawlerWithProxy) {
        try {
            CrawlingResult crawlingResult = crawlerWithProxy.instagramCrawling(place, START_TRY_COUNT);
            return Optional.of(crawlingResult);
        } catch (InstagramSchedulerException e) {
            return Optional.empty();
        }
    }
}
