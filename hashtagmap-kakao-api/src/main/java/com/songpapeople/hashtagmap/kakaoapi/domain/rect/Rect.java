package com.songpapeople.hashtagmap.kakaoapi.domain.rect;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Location;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Rect {
    private static final String COMMA = ",";

    private Location minLatitude;
    private Location maxLatitude;
    private Location minLongitude;
    private Location maxLongitude;

    public Rect(Location latitude, Location longitude, double offset) {
        this(latitude, latitude.forward(BigDecimal.valueOf(offset)),
                longitude, longitude.forward(BigDecimal.valueOf(offset)));
    }


    public Rect(Location latitude, Location longitude, BigDecimal offset) {
        this(latitude, latitude.forward(offset), longitude, longitude.forward(offset));
    }

    private Rect(Location minLatitude, Location maxLatitude, Location maxLongitude, Location minLongitude) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
    }

    public String toKakaoFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(maxLongitude.getValue())
                .append(COMMA)
                .append(minLatitude.getValue())
                .append(COMMA)
                .append(minLongitude.getValue())
                .append(COMMA)
                .append(maxLatitude.getValue());
        return stringBuilder.toString();
    }
}
