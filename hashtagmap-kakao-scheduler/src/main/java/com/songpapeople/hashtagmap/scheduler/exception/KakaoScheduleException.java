package com.songpapeople.hashtagmap.scheduler.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoScheduleException extends HashtagMapException {
    public KakaoScheduleException(final KakaoScheduleErrorCode kakaoScheduleErrorCode) {
        super(kakaoScheduleErrorCode.getMessgae(), kakaoScheduleErrorCode.getCode());
    }

    public KakaoScheduleException(final KakaoScheduleErrorCode kakaoScheduleErrorCode, final String detailMessage) {
        super(kakaoScheduleErrorCode.getMessgae(), kakaoScheduleErrorCode.getCode(), detailMessage);
    }
}
