package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoSchedulerExcpetion extends HashtagMapException {
    public KakaoSchedulerExcpetion(KakaoSchedulerExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage(), exceptionStatus.getStatusCode());
    }
}
