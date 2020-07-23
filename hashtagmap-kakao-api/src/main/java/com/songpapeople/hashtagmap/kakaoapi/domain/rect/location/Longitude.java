package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import java.math.BigDecimal;

// 경도, x
public class Longitude extends Coordinate {
    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(124);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(132);

    public Longitude(double longitude) {
        super(validateRange(BigDecimal.valueOf(longitude)));
    }

    public Longitude(BigDecimal longitude) {
        super(validateRange(longitude));
    }

    protected static BigDecimal validateRange(BigDecimal longitude) {
        if (isBetween(longitude, MIN_LONGITUDE, MAX_LONGITUDE)) {
            return longitude;
        }
        throw new IllegalArgumentException("경도 범위가 아닙니다.");
    }

    @Override
    public Coordinate forward(BigDecimal offset) {
        return new Longitude(super.value.subtract(offset));
    }
}
