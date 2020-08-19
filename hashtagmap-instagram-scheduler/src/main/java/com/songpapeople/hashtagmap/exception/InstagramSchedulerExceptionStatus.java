package com.songpapeople.hashtagmap.exception;

import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import lombok.Getter;

@Getter
public enum InstagramSchedulerExceptionStatus {
    NON_EXIST_HASHTAG("인스타그램에서 찾을 수 없는 장소입니다.", "INSTAGRAM_SCHEDULER_1000"),
    NOT_ENOUGH_HASHTAG_COUNT(String.format("해시태그 검색 결과가 %d개 미만입니다.", CrawlingResult.MIN_HASHTAG_COUNT), "INSTAGRAM_SCHEDULER_1001");

    private final String message;
    private final String statusCode;

    InstagramSchedulerExceptionStatus(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
