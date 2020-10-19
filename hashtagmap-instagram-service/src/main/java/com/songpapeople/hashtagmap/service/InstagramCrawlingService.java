package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class InstagramCrawlingService {
    private final BlackListRepository blackListRepository;
    private final InstagramCrawler instagramCrawler;

    public Optional<CrawlingResult> createCrawlingResult(Place place) {
        if (isSkipPlace(place)) {
            return Optional.empty();
        }
        String hashtagNameToCrawl = findHashtagNameToCrawl(place);
        CrawlerWithProxy crawlerWithProxy = new CrawlerWithProxy(new ProxySetter(ProxiesFactory.create()), instagramCrawler);
        return crawlerWithProxy.crawlInstagram(place, hashtagNameToCrawl);
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
