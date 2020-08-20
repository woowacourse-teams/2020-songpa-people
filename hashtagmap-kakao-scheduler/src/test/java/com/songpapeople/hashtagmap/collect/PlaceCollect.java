package com.songpapeople.hashtagmap.collect;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoSchedulerTask;
import com.songpapeople.hashtagmap.scheduler.domain.factory.PlaceFactory;
import com.songpapeople.hashtagmap.scheduler.domain.factory.RectFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("collect")
@SpringBootTest
public class PlaceCollect {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private KakaoApiService kakaoApiService;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private KakaoSchedulerTask kakaoSchedulerTask;

    @Disabled
    @Test
    void zoneRepositoryInitTest() {
        List<Zone> zones = zoneRepository.findAll();
        assertThat(zones).hasSize(25);
    }

    @Disabled
    @Test
    void findActiveZoneTest() {
        List<Zone> activeZones = zoneRepository.findByActivated();
        assertThat(activeZones).hasSize(1);
    }

    @Disabled
    @Test
    void collectPlace() {
        kakaoSchedulerTask.collectData();
    }

    @Disabled
    @Test
    void customCollectPlace() {
        List<Zone> zones = zoneRepository.findByActivated();
        List<Rect> rects = RectFactory.from(zones);
        List<Rect> otherRects = new ArrayList<>(rects.subList(19, 25));

        for (Rect rect : otherRects) {
            for (Category category : Category.values()) {
                List<KakaoPlaceDto> kakaoPlaceDtos = findPlacesByRect(category, rect);

                Set<Document> documents = kakaoPlaceDtos.stream()
                        .flatMap(kakaoPlaceDto -> kakaoPlaceDto.getDocuments().stream())
                        .collect(Collectors.toSet());

                List<Place> places = PlaceFactory.from(documents);
                savePlaces(places);
            }
        }
    }

    private List<KakaoPlaceDto> findPlacesByRect(Category category, Rect rect) {
        return kakaoApiService.findPlaces(category.getCategoryGroupCode(), rect);
    }

    @Transactional
    void savePlaces(List<Place> places) {
        placeRepository.updateAndInsert(places);
    }
}
