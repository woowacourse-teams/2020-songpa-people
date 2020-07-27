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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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

    // TODO: 2020/07/23 데이터를 받았을 때 기존 데이터 업데이트, 갱신 로직이 필요하다.
    public void collectData() {
        List<Zone> zones = zoneRepository.findByActivated();
        List<Rect> rects = RectFactory.from(zones);

        List<KakaoPlaceDto> kakaoPlaceDtos = new ArrayList<>();
        for (Rect rect : rects) {
            for (Category category : Category.values()) {
                kakaoPlaceDtos.addAll(findPlacesByRect(category, rect));
            }
        }

        Set<Document> documents = kakaoPlaceDtos.stream()
                .flatMap(kakaoPlaceDto -> kakaoPlaceDto.getDocuments().stream())
                .collect(Collectors.toSet());
        List<Place> places = PlaceFactory.from(documents);

        placeRepository.saveAll(places);
    }

    public List<KakaoPlaceDto> findPlacesByRect(Category category, Rect rect) {
        return kakaoApiService.findPlaces(category.getCategoryGroupCode(), rect);
    }
}
