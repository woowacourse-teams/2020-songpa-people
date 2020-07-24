package com.songpapeople.hashtagmap.scheduler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;

@Component
public class InstagramScheduler {
    private final InstagramScheduleService instagramScheduleService;

    public InstagramScheduler(InstagramScheduleService instagramScheduleService) {
        this.instagramScheduleService = instagramScheduleService;
    }

    public void update() {
        List<Place> places = instagramScheduleService.findAllPlace();
        List<InstagramPost> instagramPosts = getInstagramPosts(places);
        instagramScheduleService.saveAllInstagramPosts(instagramPosts);
    }

    private List<InstagramPost> getInstagramPosts(List<Place> places) {
        List<CrawlingResult> crawlingResults = instagramScheduleService.createCrawlingResult(places);
        return crawlingResults.stream()
            .map(CrawlingResult::toInstagramPosts)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
