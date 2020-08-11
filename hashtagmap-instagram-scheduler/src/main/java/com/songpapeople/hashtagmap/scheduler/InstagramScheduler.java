package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstagramScheduler {
    private final InstagramPostRepository instagramPostRepository;
    private final PlaceRepository placeRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramScheduleService instagramScheduleService;

    @Transactional
    public void update() {
        instagramPostRepository.deleteAll();
        List<Place> places = placeRepository.findAll();
        List<CrawlingResult> crawlingResults = places.stream()
                .map(instagramScheduleService::createCrawlingResult)
                .filter(Optional::isPresent)
                .map(optional -> optional.orElseThrow(NullPointerException::new))
                .collect(Collectors.toList());

        for (CrawlingResult crawlingResult : crawlingResults) {
            Instagram instagram = instagramRepository.save(crawlingResult.createInstagram());
            List<InstagramPost> instagramPosts = crawlingResult.toInstagramPosts(instagram.getId());
            instagramPostRepository.saveAll(instagramPosts);
        }
    }
}
