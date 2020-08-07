package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoSchedulerException extends HashtagMapException {
    public KakaoSchedulerException(KakaoSchedulerExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage(), exceptionStatus.getStatusCode());
    }
}
