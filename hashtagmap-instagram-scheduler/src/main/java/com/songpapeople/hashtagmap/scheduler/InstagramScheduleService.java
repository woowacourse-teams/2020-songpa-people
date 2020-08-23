package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
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
    private final BlackListRepository blackListRepository;
    private final InstagramCrawler instagramCrawler;

    public Optional<CrawlingResult> createCrawlingResult(Place place) {
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(
                new ProxySetter(ProxiesFactory.create()), instagramCrawler, this);

        return crawlerWithProxy.crawlInstagram(place, START_TRY_COUNT);
    }

    @Transactional
    public Instagram updateBlackLists(String replaceName, Instagram instagramToUpdate) {
        CrawlingDto crawlingDto = instagramCrawler.crawler(replaceName);
        instagramToUpdate.setHashtagName(replaceName);
        instagramToUpdate.setHashtagCount(crawlingDto.getHashtagCount());
        Instagram instagram = instagramRepository.save(instagramToUpdate);
        updateBlackListInstagramPost(instagram, crawlingDto);
        return instagram;
    }

    public void updateBlackListInstagramPost(Instagram instagram, CrawlingDto crawlingDto) {
        instagramPostsRepository.deleteByInstagramId(instagram.getId());
        List<PostDto> postDtos = crawlingDto.getPostDtoList();
        List<InstagramPost> instagramPosts = postDtos.stream()
                .map(postDto -> InstagramPost.builder()
                        .instagramId(instagram.getId())
                        .postUrl(postDto.getPostUrl())
                        .imageUrl(postDto.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        instagramPostsRepository.saveAll(instagramPosts);
    }

    public String findHashtagNameToCrawl(Place place) {
        return blackListRepository.findByPlaceId(place.getId())
                .map(BlackList::getReplaceName)
                .orElseGet(place::getPlaceName);
    }

    public boolean isSkipPlace(Place place) {
        return blackListRepository.findByPlaceId(place.getId())
                .map(BlackList::getIsSkipPlace)
                .orElseGet(()->false);
    }
}
