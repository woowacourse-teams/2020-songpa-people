package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public class HashtagMapException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public HashtagMapException(final String errorMessage, final String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HashtagMapException(final String errorMessage, final String errorCode, final String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
