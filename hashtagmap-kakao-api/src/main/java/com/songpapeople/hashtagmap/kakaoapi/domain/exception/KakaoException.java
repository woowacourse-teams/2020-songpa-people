package com.songpapeople.hashtagmap.kakaoapi.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoException extends RuntimeException {
    private final int statusCode;
    private final String messgae;


}
