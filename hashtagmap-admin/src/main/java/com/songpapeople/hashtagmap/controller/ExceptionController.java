package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(KakaoSchedulerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Void> kakaoSchedulerException(KakaoSchedulerException exception) {
        return CustomResponse.error(exception.getErrorCode(), exception.getMessage());
    }
}