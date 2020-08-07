package com.songpapeople.hashtagmap.scheduler.exception;

import lombok.Getter;

@Getter
public enum KakaoSchedulerErrorCode {
    SCHEDULE_ALREADY_RUNNING("KAKAO_SCHEDULE_1000", "스케쥴러가 이미 실행중입니다."),
    INVALID_PERIOD_EXPRESSION("KAKAO_SCHEDULER_1001","기간(정규식)이 잘못되었습니다.");

    private final String code;
    private final String message;


    KakaoSchedulerErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
