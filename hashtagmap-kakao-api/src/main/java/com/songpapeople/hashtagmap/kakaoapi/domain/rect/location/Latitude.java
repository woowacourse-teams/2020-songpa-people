package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiException;
import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiExceptionStatus;

import java.math.BigDecimal;

// 위도, y
public class Latitude extends Coordinate {
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
        String detailMessage = String.format("잘못된 위도 범위(%s)입니다.", latitude);
        throw new KakaoApiException(KakaoApiExceptionStatus.INVALID_LATITUDE, detailMessage);
    }

    @Override
    public Latitude forward(BigDecimal offset) {
        return new Latitude(super.value.add(offset));
    }
}
