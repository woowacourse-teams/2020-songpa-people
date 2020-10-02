package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
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
public class InstagramCrawlingService {
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostsRepository;
    private final BlackListRepository blackListRepository;
    private final InstagramCrawler instagramCrawler;

    public Optional<CrawlingResult> createCrawlingResult(Place place) {
        if (isSkipPlace(place)) {
            return Optional.empty();
        }
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(new ProxySetter(ProxiesFactory.create()), instagramCrawler);
        String hashtagNameToCrawl = findHashtagNameToCrawl(place);
        return crawlerWithProxy.crawlInstagram(place, hashtagNameToCrawl);
    }

    @Transactional
    public Instagram updateInstagramByBlackList(String kakaoId, String replaceName) {
        saveOrUpdateBlackList(new BlackList(kakaoId, replaceName));

        Instagram instagram = instagramRepository.findByKakaoIdFetch(kakaoId);
        CrawlingDto crawlingDto = instagramCrawler.crawler(replaceName);
        instagram.updateInstagram(replaceName, crawlingDto.getHashtagCount());
        instagramRepository.save(instagram);
        updateInstagramPost(instagram, crawlingDto);
        return instagram;
    }

    private void saveOrUpdateBlackList(BlackList blackListToSave) {
        Optional<BlackList> blackListByPlaceId = blackListRepository.findByKakaoId(blackListToSave.getKakaoId());
        blackListByPlaceId.ifPresent(blackList -> blackList.updateBlackList(blackListToSave));
        blackListRepository.save(blackListByPlaceId.orElseGet(() -> blackListToSave));
    }

    private void updateInstagramPost(Instagram instagram, CrawlingDto crawlingDto) {
        instagramPostsRepository.deleteByInstagramId(instagram.getId());
        List<InstagramPost> instagramPosts = crawlingDto.getPostDtoList().stream()
                .map(postDto -> InstagramPost.builder()
                        .instagram(instagram)
                        .postUrl(postDto.getPostUrl())
                        .imageUrl(postDto.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        instagramPostsRepository.saveAll(instagramPosts);
    }

    private String findHashtagNameToCrawl(Place place) {
        return blackListRepository.findByKakaoId(place.getKakaoId())
                .map(BlackList::getReplaceName)
                .orElseGet(place::getPlaceName);
    }

    private boolean isSkipPlace(Place place) {
        return blackListRepository.findByKakaoId(place.getKakaoId())
                .map(BlackList::getIsSkipPlace)
                .orElse(false);
    }
}
