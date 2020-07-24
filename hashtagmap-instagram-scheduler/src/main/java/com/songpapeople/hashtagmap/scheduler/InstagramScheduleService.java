package com.songpapeople.hashtagmap.scheduler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.exception.CrawlingUrlException;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;

@Component
public class InstagramScheduleService {
    private static final int START_TRY_COUNT = 0;
    private final PlaceRepository placeRepository;
    private final InstagramPostRepository instagramPostRepository;

    public InstagramScheduleService(PlaceRepository placeRepository, InstagramPostRepository instagramPostRepository) {
        this.placeRepository = placeRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    public List<CrawlingResult> createCrawlingResult(List<Place> places) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
            new ProxySetter(ProxiesFactory.create()), new InstagramCrawler());

        return places.stream()
            .map(place -> createCrawlingResult(place, crawlerWithProxy))
            .filter(Optional::isPresent)
            .map(crawlingResult -> crawlingResult.orElseThrow(NullPointerException::new))
            .collect(Collectors.toList());
    }

    // TODO: 2020-07-24 Slf4j 적용해보기?
    private Optional<CrawlingResult> createCrawlingResult(Place place, CrawlerWithProxy crawlerWithProxy) {
        try {
            CrawlingResult crawlingResult = crawlerWithProxy.instagramCrawling(place, START_TRY_COUNT);
            return Optional.of(crawlingResult);
        } catch (CrawlingUrlException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public void saveAllInstagramPosts(List<InstagramPost> instagramPosts) {
        instagramPostRepository.saveAll(instagramPosts);
    }

    public List<Place> findAllPlace() {
        return placeRepository.findAll();
    }
}
