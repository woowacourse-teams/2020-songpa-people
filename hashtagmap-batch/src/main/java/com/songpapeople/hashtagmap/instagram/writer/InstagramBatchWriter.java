package com.songpapeople.hashtagmap.instagram.writer;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InstagramBatchWriter implements ItemWriter<Optional<CrawlingResult>> {
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Override
    public void write(List<? extends Optional<CrawlingResult>> items) {
        List<CrawlingResult> crawlingResults = items.stream()
                .filter(Optional::isPresent)
                .map(item -> item.orElseThrow(NullPointerException::new))
                .collect(Collectors.toList());
        saveCrawlingResult(crawlingResults);
    }

    @Transactional
    public void saveCrawlingResult(List<CrawlingResult> crawlingResults) {
        List<Instagram> instagrams = instagramRepository.findAllFetch();
        List<Place> places = instagrams.stream()
                .map(Instagram::getPlace)
                .collect(Collectors.toList());
        for (CrawlingResult crawlingResult : crawlingResults) {
            Place crawlingPlace = crawlingResult.getPlace();
            deleteDuplicateInstagram(places, crawlingPlace);

            Instagram instagram = instagramRepository.save(crawlingResult.createInstagram());
            List<InstagramPost> instagramPosts = crawlingResult.toInstagramPosts(instagram.getId());
            instagramPostRepository.deleteByInstagramId(instagram.getId());
            instagramPostRepository.saveAll(instagramPosts);
        }
    }

    private void deleteDuplicateInstagram(List<Place> places, Place crawlingPlace) {
        if (places.contains(crawlingPlace)) {
            instagramRepository.deleteByPlaceId(crawlingPlace.getId());
        }
    }
}
