package com.songpapeople.hashtagmap.controller.exceptionhandler;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdminExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CustomResponse<Void> handleException(Exception e) {
        log.error("Unexpected Exception : {}", e.getMessage());
        return CustomResponse.error(CommonExceptionStatus.UNEXPECTED.getCode(), CommonExceptionStatus.UNEXPECTED.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("Wrong Argument Exception : {}", e.getMessage());
        return CustomResponse.error(CommonExceptionStatus.WRONG_ARGUMENT.getCode(), CommonExceptionStatus.WRONG_ARGUMENT.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdminException.class)
    public CustomResponse<Void> handleAdminException(AdminException e) {
        log.info("Admin Exception : {}", e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(InstagramSchedulerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<Void> handleInstagramSchedulerException(InstagramSchedulerException e) {
        log.info("InstagramScheduler Exception : {}", e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(KakaoSchedulerException.class)
    public CustomResponse<Void> handleKakaoScheduleException(KakaoSchedulerException e) {
        log.info("KakaoSchedulerException : {}", e.getMessage());
        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException : {}", e.getMessage());
        return CustomResponse.error(CommonExceptionStatus.WRONG_ARGUMENT.getCode(), CommonExceptionStatus.WRONG_ARGUMENT.getMessage());
    }
}
