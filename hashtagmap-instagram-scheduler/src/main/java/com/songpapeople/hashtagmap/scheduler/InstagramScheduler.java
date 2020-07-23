package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.repository.InstagramPostRepository;
import com.songpapeople.hashtagmap.mapper.Mapper;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;

import java.util.List;

public class InstagramScheduler {
    private final InstagramCrawler instagramCrawler;
    private final PlaceRepository placeRepository;
    private final InstagramPostRepository instagramPostRepository;
    private final ProxySetter proxySetter = new ProxySetter(ProxiesFactory.create());

    public InstagramScheduler(PlaceRepository placeRepository,
                              InstagramPostRepository instagramPostRepository) {
        this.instagramCrawler = new InstagramCrawler();
        this.placeRepository = placeRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    public void crawlingWithProxy() {
        List<Place> places = placeRepository.findAll();
        for (Place place : places) {
            proxySetter.set();
            CrawlingDto crawlingDto = instagramCrawler.crawling(place.getPlaceName());
            Instagram instagram = Mapper.toInstagram(crawlingDto, place);
            List<InstagramPost> instagramPosts =
                    Mapper.toInstagramPosts(crawlingDto.getPostDtos(), instagram);

            instagramPostRepository.saveAll(instagramPosts);
        }
    }
}
