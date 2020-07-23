package com.songpapeople.hashtagmap.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.Proxies;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;

public class InstagramScheduler {
    private final InstagramCrawler instagramCrawler;
    private final PlaceRepository placeRepository;
    private final InstagramPostRepository instagramPostRepository;

    public InstagramScheduler(PlaceRepository placeRepository,
        InstagramPostRepository instagramPostRepository) {
        this.instagramCrawler = new InstagramCrawler();
        this.placeRepository = placeRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    public void crawlingWithProxy() {
        List<Place> places = placeRepository.findAll();
        Proxies proxies = ProxiesFactory.create();
        Random random = new Random();
        List<CrawlingDto> crawlingDtos = new ArrayList<>();
        for (Place place : places) {
            proxies.setHostAndPort(random.nextInt(proxies.size()));
            crawlingDtos.add(instagramCrawler.crawling(place.getPlaceName()));
        }
        for (CrawlingDto crawlingDto : crawlingDtos) {
            Instagram instagram;
            InstagramPost instagramPost;
        }


    }

}
