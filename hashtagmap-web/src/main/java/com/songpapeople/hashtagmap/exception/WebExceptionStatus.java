package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum WebExceptionStatus {
    INVALID_TAG_LEVEL("WEB_1000", "가게에 맞는 태그레벨을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    WebExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
