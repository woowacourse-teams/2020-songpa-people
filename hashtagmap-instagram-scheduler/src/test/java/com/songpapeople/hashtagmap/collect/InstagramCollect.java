package com.songpapeople.hashtagmap.collect;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ActiveProfiles("collect")
@SpringBootTest
public class InstagramCollect {
    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramScheduleService instagramScheduleService;

    @Disabled
    @Test
    void collectInstagram() {
        /*
            여기는 테스트 코드에서 findAll()해온 place를 나눠서 진행한게 한번에 넣고 테스트를 돌리면
            가끔씩 에러가 나면서 멈추는 경우가 생겨서 여러 단계로 나눠서 데이터를 수집했습니다.
         */
        List<Place> places = placeRepository.findAll();
//        List<Place> places1 = new ArrayList<>(places.subList(0, 10000));
//        List<Place> places2 = new ArrayList<>(places.subList(10000, 20000));
//        List<Place> places3 = new ArrayList<>(places.subList(20000, 30000));
        List<Place> places4 = new ArrayList<>(places.subList(30000, 37709));

        try {
            List<CrawlingResult> crawlingResults = places4.parallelStream()
                    .map(instagramScheduleService::createCrawlingResult)
                    .filter(Optional::isPresent)
                    .map(optional -> optional.orElseThrow(NullPointerException::new))
                    .collect(Collectors.toList());
            saveData(crawlingResults);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println("catch error");
        }
    }

    @Transactional
    void saveData(List<CrawlingResult> crawlingResults) {
        for (CrawlingResult crawlingResult : crawlingResults) {
            Instagram instagram = instagramRepository.save(crawlingResult.createInstagram());
            List<InstagramPost> instagramPosts = crawlingResult.toInstagramPosts(instagram.getId());
            instagramPostRepository.saveAll(instagramPosts);
        }
    }
}
