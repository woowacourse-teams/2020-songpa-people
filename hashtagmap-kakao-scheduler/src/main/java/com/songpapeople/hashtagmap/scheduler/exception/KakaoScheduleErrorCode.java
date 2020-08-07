package com.songpapeople.hashtagmap.scheduler.exception;

import lombok.Getter;

@Getter
public enum KakaoScheduleErrorCode {
    SCHEDULE_ALREADY_RUNNING("KAKAO_SCHEDULE_1000", "스케쥴러가 이미 실행중입니다.");

    private final String code;
    private final String message;


    KakaoScheduleErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
