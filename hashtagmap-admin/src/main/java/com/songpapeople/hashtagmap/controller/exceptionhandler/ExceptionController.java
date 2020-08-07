package com.songpapeople.hashtagmap.controller.exceptionhandler;

import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InstagramSchedulerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Void> methodArgumentNotValidException(InstagramSchedulerException e) {
        return CustomResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(KakaoSchedulerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Void> kakaoSchedulerException(KakaoSchedulerException exception) {
        return CustomResponse.error(exception.getErrorCode(), exception.getMessage());
    }
}
