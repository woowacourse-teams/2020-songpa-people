package com.songpapeople.hashtagmap.scheduler.exception;

import lombok.Getter;

@Getter
public enum KakaoScheduleErrorCode {
    SCHEDULE_ALREADY_RUNNING("KAKAO_SCEHDULE_1000", "스케쥴러가 이미 실행중입니다.");

    private final String code;
    private final String messgae;


    KakaoScheduleErrorCode(final String code, final String messgae) {
        this.code = code;
        this.messgae = messgae;
    }
}
