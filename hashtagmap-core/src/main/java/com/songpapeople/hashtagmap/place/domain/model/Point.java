package com.songpapeople.hashtagmap.place.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Point {
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(43);
    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(33);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(132);
    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(124);

    private String latitude;
    private String longitude;

    @Builder
    public Point(final String latitude, final String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        validate();
    }

    private void validate() {
        BigDecimal latitude = BigDecimal.valueOf(Double.parseDouble(this.latitude));
        BigDecimal longitude = BigDecimal.valueOf(Double.parseDouble(this.longitude));
        if (isNotBetween(MIN_LATITUDE, latitude, MAX_LATITUDE)) {
            throw new IllegalArgumentException();
        }
        if (isNotBetween(MIN_LONGITUDE, longitude, MAX_LONGITUDE)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotBetween(BigDecimal min, BigDecimal value, BigDecimal max) {
        return !(value.compareTo(max) <= 0
                && value.compareTo(min) >= 0);
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(this.getLatitude(), point.getLatitude())
                && Objects.equals(this.getLongitude(), point.getLongitude());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(this.getLatitude(), this.getLongitude());
    }
}