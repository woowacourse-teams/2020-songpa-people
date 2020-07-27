package com.songpapeople.hashtagmap.exception;

public class InstagramSchedulerException extends HashtagMapException {

    public InstagramSchedulerException(InstagramSchedulerExceptionStatus instagramSchedulerExceptionStatus) {
        super(instagramSchedulerExceptionStatus.getMessage(), instagramSchedulerExceptionStatus.getStatusCode());
    }
}
