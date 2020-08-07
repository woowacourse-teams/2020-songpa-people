package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoSchedulerException extends HashtagMapException {
    public KakaoSchedulerException(final KakaoSchedulerErrorCode kakaoSchedulerErrorCode) {
        super(kakaoSchedulerErrorCode.getMessage(), kakaoSchedulerErrorCode.getCode());
    }

    public KakaoSchedulerException(final KakaoSchedulerErrorCode kakaoSchedulerErrorCode, final String detailMessage) {
        super(kakaoSchedulerErrorCode.getMessage(), kakaoSchedulerErrorCode.getCode(), detailMessage);
    }
}
