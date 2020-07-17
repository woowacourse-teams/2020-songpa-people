package com.songpapeople.hashtagmap.kakaoapi.domain.rect;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Location;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rect rect = (Rect) o;
        return Objects.equals(minLatitude.getValue(), rect.minLatitude.getValue()) &&
                Objects.equals(maxLatitude.getValue(), rect.maxLatitude.getValue()) &&
                Objects.equals(minLongitude.getValue(), rect.minLongitude.getValue()) &&
                Objects.equals(maxLongitude.getValue(), rect.maxLongitude.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }
}
