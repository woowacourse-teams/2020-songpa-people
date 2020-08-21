package com.songpapeople.hashtagmap.kakaoapi.exception;

import com.songpapeople.hashtagmap.exception.HashtagMapException;

public class KakaoApiException extends HashtagMapException {
    public KakaoApiException(KakaoApiExceptionStatus kakaoApiExceptionStatus) {
        super(kakaoApiExceptionStatus.getMessage(), kakaoApiExceptionStatus.getCode());
    }

    public KakaoApiException(KakaoApiExceptionStatus kakaoApiExceptionStatus, String detailMessgae) {
        super(kakaoApiExceptionStatus.getMessage(), kakaoApiExceptionStatus.getCode(), detailMessgae);
    }
}