package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

public class InstagramScheduleController {
    private final InstagramScheduleService instagramScheduleService;

    public InstagramScheduleController(InstagramScheduleService instagramScheduleService) {
        this.instagramScheduleService = instagramScheduleService;
    }

    public void update() {
        List<Place> places = instagramScheduleService.findAllPlace();
        List<InstagramPost> instagramPosts = getInstagramPosts(places);
        instagramScheduleService.saveAllInstagramPosts(instagramPosts);
    }

    private List<InstagramPost> getInstagramPosts(List<Place> places) {
        List<CrawlingResult> crawlingResults =
                instagramScheduleService.createCrawlingResult(places);
        List<InstagramPost> instagramPosts = new ArrayList<>();
        for (CrawlingResult crawlingResult : crawlingResults) {
            instagramPosts.addAll(crawlingResult.toInstagramPosts());
        }
        return instagramPosts;
    }
}
