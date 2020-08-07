package com.songpapeople.hashtagmap.controller.exceptionhandler;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdminExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdminException.class)
    public CustomResponse<Void> handleAdminException(AdminException e) {
        log.error(e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(InstagramSchedulerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Void> methodArgumentNotValidException(InstagramSchedulerException e) {
        log.error(e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(KakaoSchedulerException.class)
    public CustomResponse<Void> handleKakaoScheduleException(KakaoSchedulerException e) {
        log.error(e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
    }
}
