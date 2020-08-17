package com.songpapeople.hashtagmap.exception;

public class WebException extends HashtagMapException {
    public WebException(WebExceptionStatus webExceptionStatus) {
        super(webExceptionStatus.getMessage(), webExceptionStatus.getCode());
    }
}
