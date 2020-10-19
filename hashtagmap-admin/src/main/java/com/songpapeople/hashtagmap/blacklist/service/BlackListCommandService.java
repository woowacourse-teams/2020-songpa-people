package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlackListCommandService {
    private final BlackListRepository blackListRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramQueryRepository instagramQueryRepository;
    private final InstagramPostRepository instagramPostRepository;
    private final InstagramCrawler instagramCrawler;

    @Transactional
    public void deleteInstagramAfterAddBlackList(BlackListRequest blackListRequest) {
        blackListRepository.save(BlackListRequest.toSkipBlackList(blackListRequest));
        Instagram instagram = instagramQueryRepository.findByKakaoId(blackListRequest.getKakaoId());
        instagramPostRepository.deleteByInstagramId(instagram.getId());
        instagramRepository.deleteById(instagram.getId());
    }

    @Transactional
    public Instagram updateInstagramByBlackList(String kakaoId, String replaceName) {
        saveOrUpdateBlackList(new BlackList(kakaoId, replaceName));
        Instagram instagram = instagramQueryRepository.findByKakaoId(kakaoId);
        CrawlingDto crawlingDto = instagramCrawler.crawler(replaceName);
        instagram.updateInstagram(replaceName, crawlingDto.getHashtagCount());
        instagramRepository.save(instagram);
        updateInstagramPost(instagram, crawlingDto);
        return instagram;
    }

    private void saveOrUpdateBlackList(BlackList blackListToSave) {
        Optional<BlackList> blackListByPlaceId = blackListRepository.findByKakaoId(blackListToSave.getKakaoId());
        blackListByPlaceId.ifPresent(blackList -> blackList.updateBlackList(blackListToSave));
        blackListRepository.save(blackListByPlaceId.orElse(blackListToSave));
    }

    private void updateInstagramPost(Instagram instagram, CrawlingDto crawlingDto) {
        instagramPostRepository.deleteByInstagramId(instagram.getId());
        List<InstagramPost> instagramPosts = crawlingDto.getPostDtoList().stream()
                .map(postDto -> InstagramPost.builder()
                        .instagram(instagram)
                        .postUrl(postDto.getPostUrl())
                        .imageUrl(postDto.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        instagramPostRepository.saveAll(instagramPosts);
    }

}
