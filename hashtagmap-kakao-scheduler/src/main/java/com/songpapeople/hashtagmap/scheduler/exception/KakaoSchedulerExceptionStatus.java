package com.songpapeople.hashtagmap.scheduler.exception;

import lombok.Getter;

@Getter
public enum KakaoSchedulerExceptionStatus {
    INVALID_PERIOD_EXPRESSION("기간(정규식)이 잘못되었습니다.", "KAKAO_SCHEDULER_1001");
    private final String message;
    private final String statusCode;

    KakaoSchedulerExceptionStatus(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
