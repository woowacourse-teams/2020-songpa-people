package com.songpapeople.hashtagmap.scheduler.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Location;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
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
        Location minLatitude = new Latitude(new BigDecimal(zone.getMinLatitude()));
        Location maxLatitude = new Latitude(new BigDecimal(zone.getMaxLatitude()));
        Location minLongitude = new Longitude(new BigDecimal(zone.getMinLongitude()));
        Location maxLongitude = new Longitude(new BigDecimal(zone.getMaxLongitude()));

        return new Rect(minLatitude, maxLatitude, maxLongitude, minLongitude);
    }

    public List<KakaoPlaceDto> findPlacesByRect(Category category, Rect rect) {
        return kakaoApiService.findPlaces(category.getCategoryGroupCode(), rect);
    }
}
