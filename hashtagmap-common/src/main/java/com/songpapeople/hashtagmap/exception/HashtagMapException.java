package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public class HashtagMapException extends RuntimeException {
    private final String errorCode;

    public HashtagMapException(final String errorMessage, final String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
