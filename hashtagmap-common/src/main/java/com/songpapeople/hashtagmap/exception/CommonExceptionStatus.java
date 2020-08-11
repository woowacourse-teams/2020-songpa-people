package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum CommonExceptionStatus {
    BIND_VALIDATION("COMMON_1000", "전달받은 매개변수가 올바르지 않습니다.");

    private final String code;
    private final String message;

    CommonExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
