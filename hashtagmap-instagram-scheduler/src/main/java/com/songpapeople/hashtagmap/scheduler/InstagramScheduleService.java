package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InstagramScheduleService {
    private static final int START_TRY_COUNT = 0;

    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostsRepository;
    private final InstagramCrawler instagramCrawler;

    public Optional<CrawlingResult> createCrawlingResult(Place place) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
                new ProxySetter(ProxiesFactory.create()), instagramCrawler);

        return crawlerWithProxy.crawlInstagram(place, START_TRY_COUNT);
    }

    @Transactional
    public Instagram updateBlackLists(String replaceName, Instagram instagramToUpdate) {
        CrawlingDto crawlingDto = instagramCrawler.crawler(replaceName);
        Instagram instagram = updateBlackListInstagram(replaceName, instagramToUpdate, crawlingDto);
        updateBlackListInstagramPost(instagramToUpdate, crawlingDto);
        return instagram;
    }

    public Instagram updateBlackListInstagram(String replaceName, Instagram instagramToUpdate, CrawlingDto crawlingDto) {
        instagramToUpdate.setHashtagName(replaceName);
        instagramToUpdate.setHashtagCount(crawlingDto.getHashtagCount());
        return saveInstagram(instagramToUpdate);
    }

    public void updateBlackListInstagramPost(Instagram instagramToUpdate, CrawlingDto crawlingDto) {
        instagramPostsRepository.deleteByInstagramId(instagramToUpdate.getId());
        List<PostDto> postDtos = crawlingDto.getPostDtoList();
        List<InstagramPost> instagramPosts = postDtos.stream()
                .map(postDto -> InstagramPost.builder()
                        .instagramId(instagramToUpdate.getId())
                        .postUrl(postDto.getPostUrl())
                        .imageUrl(postDto.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        saveAllInstagramPosts(instagramPosts);
    }

    @Transactional
    public void saveAllInstagramPosts(List<InstagramPost> instagramPosts) {
        instagramPostsRepository.saveAll(instagramPosts);
    }

    @Transactional
    public Instagram saveInstagram(Instagram instagram) {
        return instagramRepository.save(instagram);
    }
}
