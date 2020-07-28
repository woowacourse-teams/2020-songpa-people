package com.songpapeople.hashtagmap.scheduler.domain.factory;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Coordinate;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.place.domain.model.Zone;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class RectFactory {
    public static List<Rect> from(List<Zone> zones) {
        return zones.stream()
                .map(RectFactory::from)
                .collect(Collectors.toList());
    }

    public static Rect from(Zone zone) {
        Coordinate minLatitude = new Latitude(new BigDecimal(zone.getMinLatitude()));
        Coordinate maxLatitude = new Latitude(new BigDecimal(zone.getMaxLatitude()));
        Coordinate minLongitude = new Longitude(new BigDecimal(zone.getMinLongitude()));
        Coordinate maxLongitude = new Longitude(new BigDecimal(zone.getMaxLongitude()));

        return new Rect(minLatitude, maxLatitude, maxLongitude, minLongitude);
    }
}
