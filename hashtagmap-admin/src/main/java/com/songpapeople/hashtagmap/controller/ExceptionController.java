package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExcpetion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(KakaoSchedulerExcpetion.class)
    public ResponseEntity<CustomResponse> kakaoSchedulerException(KakaoSchedulerExcpetion exception) {
        return ResponseEntity.ok(CustomResponse.error(exception.getErrorCode(), exception.getMessage()));
    }
}