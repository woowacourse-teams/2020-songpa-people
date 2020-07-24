package com.songpapeople.hashtagmap.scheduler;

import java.util.ArrayList;
import java.util.List;

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
    private final PlaceRepository placeRepository;
    private final InstagramPostRepository instagramPostRepository;

    public InstagramScheduleService(PlaceRepository placeRepository, InstagramPostRepository instagramPostRepository) {
        this.placeRepository = placeRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    public List<CrawlingResult> createCrawlingResult(List<Place> places) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
            new ProxySetter(ProxiesFactory.create()), new InstagramCrawler());
        List<CrawlingResult> crawlingResults = new ArrayList<>();
        CrawlingResult crawlingResult;
        for (Place place : places) {
            try {
                crawlingResult = crawlerWithProxy.instagramCrawling(place, 0);
            } catch (CrawlingUrlException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            crawlingResults.add(crawlingResult);
        }
        return crawlingResults;
    }

    public void saveAllInstagramPosts(List<InstagramPost> instagramPosts) {
        instagramPostRepository.saveAll(instagramPosts);
    }

    public List<Place> findAllPlace() {
        return placeRepository.findAll();
    }
}
