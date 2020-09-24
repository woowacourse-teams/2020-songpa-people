package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InstagramService {
    private final InstagramCrawlingService instagramCrawlingService;
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;
    private final PlaceRepository placeRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update() {
        List<Place> places = placeRepository.findAll();
        List<CrawlingResult> crawlingResults = places.stream()
                .map(instagramCrawlingService::createCrawlingResult)
                .filter(Optional::isPresent)
                .map(optional -> optional.orElseThrow(NullPointerException::new))
                .collect(Collectors.toList());

        saveCrawlingResults(crawlingResults);
    }

    @Transactional
    public void saveCrawlingResults(List<CrawlingResult> crawlingResults) {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        for (CrawlingResult crawlingResult : crawlingResults) {
            Instagram instagram = instagramRepository.save(crawlingResult.createInstagram());
            List<InstagramPost> instagramPosts = crawlingResult.toInstagramPosts(instagram.getId());
            instagramPostRepository.saveAll(instagramPosts);
        }
    }
}
