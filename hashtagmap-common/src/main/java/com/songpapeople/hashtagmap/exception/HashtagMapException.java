package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public class HashtagMapException extends RuntimeException {

    private final String errorMessage;
    private final String errorCode;

    public HashtagMapException(final String errorMessage, final String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    // TODO: 2020-07-27 super 안해도되나? 
}
