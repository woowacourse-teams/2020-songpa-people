package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import java.math.BigDecimal;

// 위도, x
public class Latitude extends Location {
    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(33);
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(43);

    public Latitude(double latitude) {
        super(validateRange(BigDecimal.valueOf(latitude)));
    }

    public Latitude(BigDecimal latitude) {
        super(validateRange(latitude));
    }

    protected static BigDecimal validateRange(BigDecimal latitude) {
        if (isBetween(latitude, MIN_LATITUDE, MAX_LATITUDE)) {
            return latitude;
        }
        throw new IllegalArgumentException("위도 범위가 아닙니다.");
    }

    @Override
    public Latitude forward(BigDecimal offset) {
        return new Latitude(super.value.add(offset));
    }
}
