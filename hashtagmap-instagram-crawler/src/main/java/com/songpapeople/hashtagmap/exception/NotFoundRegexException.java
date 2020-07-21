package com.songpapeople.hashtagmap.exception;

public class NotFoundRegexException extends RuntimeException {

    public NotFoundRegexException() {
        super("원하는 내용을 찾지 못했습니다.");
    }
}
