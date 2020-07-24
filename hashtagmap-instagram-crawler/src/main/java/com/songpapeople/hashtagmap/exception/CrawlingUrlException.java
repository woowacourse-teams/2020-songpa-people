package com.songpapeople.hashtagmap.exception;

public class CrawlingUrlException extends RuntimeException {

    public CrawlingUrlException(String url) {
        super("연결할 수 없습니다, " + url);
    }
}
