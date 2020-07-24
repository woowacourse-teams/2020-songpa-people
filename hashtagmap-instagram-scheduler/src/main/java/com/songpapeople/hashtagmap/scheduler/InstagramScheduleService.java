package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstagramScheduleService {
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
                .map(place -> crawlerWithProxy.instagramCrawling(place, 0))
                .collect(Collectors.toList());
    }

    public void saveAllInstagramPosts(List<InstagramPost> instagramPosts) {
        instagramPostRepository.saveAll(instagramPosts);
    }

    public List<Place> findAllPlace() {
        return placeRepository.findAll();
    }
}
