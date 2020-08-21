package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
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

    private void validate(String latitude, String longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
    }

    private void validateLatitude(String latitude) {
        BigDecimal convertedLatitude = BigDecimal.valueOf(Double.parseDouble(latitude));
        if (isNotBetween(MIN_LATITUDE, convertedLatitude, MAX_LATITUDE)) {
            String detailMessage = String.format("잘못된 위도 범위(%s)입니다.", convertedLatitude);
            throw new CoreException(CoreExceptionStatus.INVALID_LATITUDE, detailMessage);
        }
    }

    private void validateLongitude(String longitude) {
        BigDecimal convertedLongitude = BigDecimal.valueOf(Double.parseDouble(longitude));
        if (isNotBetween(MIN_LONGITUDE, convertedLongitude, MAX_LONGITUDE)) {
            String detailMessage = String.format("잘못된 경도 범위(%s)입니다.", convertedLongitude);
            throw new CoreException(CoreExceptionStatus.INVALID_LONGITUDE, detailMessage);
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