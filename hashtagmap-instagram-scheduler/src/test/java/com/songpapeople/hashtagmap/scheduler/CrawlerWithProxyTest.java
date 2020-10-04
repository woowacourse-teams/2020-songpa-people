package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.Crawler;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.proxy.ProxiesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@ExtendWith(MockitoExtension.class)
@ActiveProfiles("dev")
@SpringBootTest
class CrawlerWithProxyTest {
    private CrawlerWithProxy crawlerWithProxy;

    @Autowired
    PlaceRepository placeRepository;

//    @Mock
//    private InstagramCrawler instagramCrawler;
//
//    @Mock
//    private ProxySetter proxySetter;

    @BeforeEach
    void setUp() {
        crawlerWithProxy = new CrawlerWithProxy(new ProxySetter(ProxiesFactory.create(new Crawler())),
                new InstagramCrawler(new Crawler()));
    }

    @DisplayName("asd")
    @Test
    void start() {
        List<Place> all = placeRepository.findAll();
        try {
            List<Optional<CrawlingResult>> collect = all.subList(0, 100).stream()
                    .map(place -> crawlerWithProxy.crawlInstagram(place, place.getPlaceName()))
                    .collect(Collectors.toList());
            System.out.println("404: " + Crawler.count404);
            System.out.println("429: " + Crawler.count429);
            int count = 0;
            for (Optional<CrawlingResult> crawlingResult : collect) {
                if (crawlingResult.isPresent()) {
                    count++;
                }
            }
            System.out.println("크롤링 성공: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @DisplayName("검색할 수 없는 Place 검색시 Optional.empty()반환")
//    @Test
//    void instagramCrawling() {
//        Place place = Place.builder()
//                .placeName("잠실타로&사주")
//                .build();
//
//        when(instagramCrawler.crawler(place.getPlaceName()))
//                .thenThrow(InstagramSchedulerException.class);
//
//        assertThat(crawlerWithProxy.crawlInstagram(place, place.getPlaceName(), 0)).isEqualTo(Optional.empty());
//    }


}
