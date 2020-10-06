package com.songpapeople.hashtagmap.instagram.writer;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InstagramBatchWriter implements ItemWriter<CrawlingResult> {
    private final InstagramRepository instagramRepository;
    private final InstagramQueryRepository instagramQueryRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Override
    public void write(List<? extends CrawlingResult> items) {
        saveCrawlingResult((List<CrawlingResult>) items);
    }

    public void saveCrawlingResult(List<CrawlingResult> crawlingResults) {
        List<Instagram> instagrams = instagramQueryRepository.findAllFetch();
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
