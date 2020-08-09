package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoSchedulerException extends HashtagMapException {
    public KakaoSchedulerException(final KakaoSchedulerExceptionStatus kakaoSchedulerExceptionStatus) {
        super(kakaoSchedulerExceptionStatus.getMessage(), kakaoSchedulerExceptionStatus.getCode());
    }

    public KakaoSchedulerException(final KakaoSchedulerExceptionStatus kakaoSchedulerExceptionStatus, final String detailMessage) {
        super(kakaoSchedulerExceptionStatus.getMessage(), kakaoSchedulerExceptionStatus.getCode(), detailMessage);
    }
}
