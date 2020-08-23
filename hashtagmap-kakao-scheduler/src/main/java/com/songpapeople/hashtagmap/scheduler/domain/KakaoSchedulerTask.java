package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import com.songpapeople.hashtagmap.scheduler.domain.factory.PlaceFactory;
import com.songpapeople.hashtagmap.scheduler.domain.factory.RectFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KakaoSchedulerTask {
    private final ZoneRepository zoneRepository;
    private final PlaceRepository placeRepository;
    private final KakaoApiService kakaoApiService;

    public KakaoSchedulerTask(ZoneRepository zoneRepository, PlaceRepository placeRepository,
                              KakaoApiService kakaoApiService) {
        this.zoneRepository = zoneRepository;
        this.placeRepository = placeRepository;
        this.kakaoApiService = kakaoApiService;
    }

    public void collectData() {
        List<Zone> zones = zoneRepository.findByActivated();
        List<Rect> rects = RectFactory.from(zones);

        for (Rect rect : rects) {
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
