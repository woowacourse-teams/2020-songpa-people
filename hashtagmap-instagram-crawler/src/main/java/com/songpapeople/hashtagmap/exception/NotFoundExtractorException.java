package com.songpapeople.hashtagmap.exception;

public class NotFoundExtractorException extends RuntimeException {

    public NotFoundExtractorException() {
        super("원하는 내용을 찾지 못했습니다.");
    }
}
