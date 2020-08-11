package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum AdminExceptionStatus {
    NOT_FOUND_SCHEDULER("ADMIN_1000", "스케쥴러가 존재하지 않습니다.");

    private final String code;
    private final String message;

    AdminExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
