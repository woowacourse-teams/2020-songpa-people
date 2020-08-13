package com.songpapeople.hashtagmap.place.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Point {
    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(33);
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(43);
    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(124);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(132);

    private String latitude;
    private String longitude;

    @Builder
    public Point(final String latitude, final String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        validate(this.latitude, this.longitude);
    }

    private void validate(final String latitude, final String longitude) {
        BigDecimal convertedLatitude = BigDecimal.valueOf(Double.parseDouble(latitude));
        BigDecimal convertedLongitude = BigDecimal.valueOf(Double.parseDouble(longitude));
        if (isNotBetween(MIN_LATITUDE, convertedLatitude, MAX_LATITUDE)) {
            throw new IllegalArgumentException(String.format("not correct latitude range %s", convertedLatitude));
        }
        if (isNotBetween(MIN_LONGITUDE, convertedLongitude, MAX_LONGITUDE)) {
            throw new IllegalArgumentException(String.format("not correct longitude range %s", convertedLatitude));
        }
    }

    private boolean isNotBetween(BigDecimal min, BigDecimal value, BigDecimal max) {
        return !(value.compareTo(max) <= 0
                && value.compareTo(min) >= 0);
    }

    public void changeLatitude(final String latitude) {
        if (!StringUtils.isEmpty(latitude)) {
            validate(latitude, this.longitude);
            this.latitude = latitude;
        }
    }

    public void changeLongitude(final String longitude) {
        if (!StringUtils.isEmpty(longitude)) {
            validate(this.latitude, longitude);
            this.longitude = longitude;
        }
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