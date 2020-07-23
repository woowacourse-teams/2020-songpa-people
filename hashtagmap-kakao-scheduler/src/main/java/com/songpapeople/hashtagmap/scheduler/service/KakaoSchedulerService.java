package com.songpapeople.hashtagmap.scheduler.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Coordinate;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KakaoSchedulerService {
    private final KakaoApiService kakaoApiService;

    public KakaoSchedulerService(KakaoApiService kakaoApiService) {
        this.kakaoApiService = kakaoApiService;
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

