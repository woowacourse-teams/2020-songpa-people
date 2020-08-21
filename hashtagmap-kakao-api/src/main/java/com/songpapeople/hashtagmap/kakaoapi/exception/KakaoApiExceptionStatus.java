package com.songpapeople.hashtagmap.kakaoapi.exception;

import lombok.Getter;

@Getter
public enum KakaoApiExceptionStatus {
    INVALID_LATITUDE("KAKAO_API_1001", "부정확한 위도 값입니다."),
    INVALID_LONGITUDE("KAKAO_API_1002", "부정확한 경도 값입니다.");

    private final String code;
    private final String message;

    KakaoApiExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
