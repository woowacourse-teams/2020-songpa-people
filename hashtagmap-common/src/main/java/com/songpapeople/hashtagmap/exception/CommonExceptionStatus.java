package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum CommonExceptionStatus {
    UNEXPECTED("COMMON_0000", "요처을 처리하지 못했습니다."),
    WRONG_ARGUMENT("COMMON_1000", "전달받은 매개변수가 올바르지 않습니다."),
    ALREADY_PERSIST("COMMON_2000", "이미 등록되었습니다."),
    NOT_PERSIST("COMMON_2100", "등록되어있지 않습니다.");

    private final String code;
    private final String message;

    CommonExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
