package com.songpapeople.hashtagmap.instagram.writer;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InstagramBatchWriter implements ItemWriter<CrawlingResult> {
    private final PlaceRepository placeRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Override
    public void write(List<? extends CrawlingResult> items) {
        saveCrawlingResult((List<CrawlingResult>) items);
    }

    public void saveCrawlingResult(List<CrawlingResult> crawlingResults) {
        List<Place> places = placeRepository.findAll();
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
