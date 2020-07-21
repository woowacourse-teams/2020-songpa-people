package com.songpapeople.hashtagmap.exception;

public class CrawlingUrlException extends RuntimeException {

    public CrawlingUrlException() {
        super("연결할 수 없는 url 입니다.");
    }
}
