package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum CoreExceptionStatus {
    INVALID_LATITUDE("CORE_1001", "부정확한 위도 값입니다."),
    INVALID_LONGITUDE("CORE_1002", "부정확한 경도 값입니다."),
    INVALID_TAG_LEVEL("CORE_1003", "해시 태그 값이 잘못되었습니다.");

    private final String code;
    private final String message;

    CoreExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
