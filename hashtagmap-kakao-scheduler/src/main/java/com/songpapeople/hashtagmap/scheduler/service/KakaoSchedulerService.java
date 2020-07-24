package com.songpapeople.hashtagmap.scheduler.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Coordinate;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.*;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KakaoSchedulerService {
    private final ZoneRepository zoneRepository;
    private final PlaceRepository placeRepository;
    private final KakaoApiService kakaoApiService;

    public KakaoSchedulerService(ZoneRepository zoneRepository, PlaceRepository placeRepository,
                                 KakaoApiService kakaoApiService) {
        this.zoneRepository = zoneRepository;
        this.placeRepository = placeRepository;
        this.kakaoApiService = kakaoApiService;
    }

    // TODO: 2020/07/23 데이터를 받았을 때 기존 데이터 업데이트, 갱신 로직이 필요하다.
    public void collectData() {
        List<Zone> zones = zoneRepository.findByActivated();
        List<Rect> rects = zones.stream()
                .map(this::zoneToRect)
                .collect(Collectors.toList());

        // TODO: 2020/07/23 dto 변환 클래스 만들기.
        List<KakaoPlaceDto> kakaoPlaceDtos = new ArrayList<>();
        for (Rect rect : rects) {
            kakaoPlaceDtos.addAll(Arrays.stream(Category.values())
                    .map(category -> findPlacesByRect(category, rect))
                    .flatMap(dtos -> dtos.stream())
                    .collect(Collectors.toList()));
        }

        List<Document> documents = kakaoPlaceDtos.stream()
                .flatMap(kakaoPlaceDto -> kakaoPlaceDto.getDocuments().stream())
                .collect(Collectors.toList());
        List<Place> places = new ArrayList<>();
        for (Document document : documents) {
            places.add(documentToPlace(document));
        }

        placeRepository.saveAll(places);
    }

    public Rect zoneToRect(Zone zone) {
        Coordinate minLatitude = new Latitude(new BigDecimal(zone.getMinLatitude()));
        Coordinate maxLatitude = new Latitude(new BigDecimal(zone.getMaxLatitude()));
        Coordinate minLongitude = new Longitude(new BigDecimal(zone.getMinLongitude()));
        Coordinate maxLongitude = new Longitude(new BigDecimal(zone.getMaxLongitude()));

        return new Rect(minLatitude, maxLatitude, maxLongitude, minLongitude);
    }

    public List<KakaoPlaceDto> findPlacesByRect(Category category, Rect rect) {
        return kakaoApiService.findPlaces(category.getCategoryGroupCode(), rect);
    }

    public Place documentToPlace(Document document) {
        Location location = new Location(new Point(document.getY(), document.getX()), document.getRoadAddressName());
        Place place = Place.builder()
                .kakaoId(document.getId())
                .placeName(document.getPlaceName())
                .category(Category.fromCategoryGroupCode(document.getCategoryGroupCode()))
                .location(location)
                .placeUrl(document.getPlaceUrl())
                .build();
        return place;
    }
}

