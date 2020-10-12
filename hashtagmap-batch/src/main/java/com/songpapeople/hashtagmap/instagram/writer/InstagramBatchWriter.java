package com.songpapeople.hashtagmap.instagram.writer;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.instagram.repository.InstagramBatchQueryRepository;
import com.songpapeople.hashtagmap.instagram.repository.dto.InstagramBatchDto;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class InstagramBatchWriter implements ItemWriter<CrawlingResult> {
    private final InstagramRepository instagramRepository;
    private final InstagramBatchQueryRepository instagramBatchQueryRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Override
    public void write(List<? extends CrawlingResult> items) {
        saveCrawlingResult((List<CrawlingResult>) items);
    }

    public void saveCrawlingResult(List<CrawlingResult> crawlingResults) {
        List<InstagramBatchDto> instagramBatchDtos = instagramBatchQueryRepository.findAll();
        for (CrawlingResult crawlingResult : crawlingResults) {
            Place crawlingPlace = crawlingResult.getPlace();
            deleteDuplicateInstagram(instagramBatchDtos, crawlingPlace);

            Instagram instagram = instagramRepository.save(crawlingResult.createInstagram());
            List<InstagramPost> instagramPosts = crawlingResult.toInstagramPosts(instagram);
            instagramPostRepository.saveAll(instagramPosts);
        }
    }

    private void deleteDuplicateInstagram(List<InstagramBatchDto> instagramBatchDtos, Place place) {
        instagramBatchDtos.stream()
                .filter(instagramBatchDto -> Objects.equals(instagramBatchDto.getPlaceId(), place.getId()))
                .findFirst()
                .ifPresent(instagramBatchDto -> deleteInstagramAndInstagramPost(instagramBatchDto));
    }

    private void deleteInstagramAndInstagramPost(InstagramBatchDto instagramBatchDto) {
        instagramPostRepository.deleteByInstagramId(instagramBatchDto.getInstagramId());
        instagramRepository.deleteById(instagramBatchDto.getInstagramId());
    }
}
