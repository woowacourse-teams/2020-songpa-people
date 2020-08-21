package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiException;
import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiExceptionStatus;

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
        String detailMessage = String.format("잘못된 경도 범위(%s)입니다.", longitude);
        throw new KakaoApiException(KakaoApiExceptionStatus.INVALID_LONGITUDE, detailMessage);
    }

    @Override
    public Coordinate forward(BigDecimal offset) {
        return new Longitude(super.value.subtract(offset));
    }
}
