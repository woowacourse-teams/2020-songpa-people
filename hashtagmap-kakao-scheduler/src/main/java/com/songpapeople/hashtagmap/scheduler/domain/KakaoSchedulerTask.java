package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import com.songpapeople.hashtagmap.scheduler.domain.event.service.KakaoEventService;
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
    private final KakaoEventService eventService;

    public KakaoSchedulerTask(ZoneRepository zoneRepository, PlaceRepository placeRepository,
                              KakaoApiService kakaoApiService, KakaoEventService kakaoEventService) {
        this.zoneRepository = zoneRepository;
        this.placeRepository = placeRepository;
        this.kakaoApiService = kakaoApiService;
        this.eventService = kakaoEventService;
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

    public void sourceEvent() {
        List<Zone> zones = zoneRepository.findByActivated();

        for (Zone zone : zones) {
            for (Category category : Category.values()) {
                eventService.provide(new KakaoEvent(eventService::collect, category, zone));
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
