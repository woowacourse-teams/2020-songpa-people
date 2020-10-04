package com.songpapeople.hashtagmap.exception;

import com.songpapeople.hashtagmap.dto.PostDtos;
import lombok.Getter;

@Getter
public enum CrawlerExceptionStatus {
    TOO_MANY_REQUEST("요청이 너무 많습니다.", "CRAWLER_1000"),
    URL_NOT_CONNECT("연결할 수 없는 URL 입니다.", "CRAWLER_1001"),
    NOT_ENOUGH_POPULAR_POST(String.format("인기게시물이 %d개 미만입니다.", PostDtos.POPULAR_POST_SIZE), "CRAWLER_1002"),
    NOT_FOUND_MATCH_REGEX("크롤링 데이터에서 원하는 내용을 찾지 못했습니다.", "CRAWLER_1003"),
    ILLEGAL_PROXY("잘못된 프록시 설정입니다.", "CRAWLER_1004");

    private final String message;
    private final String statusCode;

    CrawlerExceptionStatus(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
