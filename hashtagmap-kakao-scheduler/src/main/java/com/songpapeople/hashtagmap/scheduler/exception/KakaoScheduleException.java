package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoScheduleException extends HashtagMapException {
    public KakaoScheduleException(final KakaoScheduleErrorCode kakaoScheduleErrorCode) {
        super(kakaoScheduleErrorCode.getMessage(), kakaoScheduleErrorCode.getCode());
    }

    public KakaoScheduleException(final KakaoScheduleErrorCode kakaoScheduleErrorCode, final String detailMessage) {
        super(kakaoScheduleErrorCode.getMessage(), kakaoScheduleErrorCode.getCode(), detailMessage);
    }
}
