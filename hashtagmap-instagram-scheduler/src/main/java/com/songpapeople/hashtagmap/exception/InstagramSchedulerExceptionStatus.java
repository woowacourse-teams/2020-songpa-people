package com.songpapeople.hashtagmap.exception;

import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import lombok.Getter;

@Getter
public enum InstagramSchedulerExceptionStatus {
    NOT_ENOUGH_HASHTAG_COUNT(String.format("해시태그 검색 결과가 %d개 미만입니다.", CrawlingResult.MIN_HASHTAG_COUNT), "INSTAGRAM_SCHEDULER_1000");

    private final String message;
    private final String statusCode;

    InstagramSchedulerExceptionStatus(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
