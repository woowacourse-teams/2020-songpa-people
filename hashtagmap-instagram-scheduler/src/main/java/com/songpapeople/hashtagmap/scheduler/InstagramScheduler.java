package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstagramScheduler {
    private final InstagramPostRepository instagramPostRepository;
    private final PlaceRepository placeRepository;
    private final InstagramScheduleService instagramScheduleService;

    public InstagramScheduler(InstagramPostRepository instagramPostRepository,
                              PlaceRepository placeRepository,
                              InstagramScheduleService instagramScheduleService) {
        this.instagramPostRepository = instagramPostRepository;
        this.placeRepository = placeRepository;
        this.instagramScheduleService = instagramScheduleService;
    }

    @Transactional
    public void update() {
        instagramPostRepository.deleteAll();
        List<Place> places = placeRepository.findAll();
        List<InstagramPost> instagramPosts = getInstagramPosts(places);
        instagramPostRepository.saveAll(instagramPosts);
    }

    private List<InstagramPost> getInstagramPosts(List<Place> places) {
        List<CrawlingResult> crawlingResults = instagramScheduleService.createCrawlingResult(places);
        return crawlingResults.stream()
                .map(CrawlingResult::toInstagramPosts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
