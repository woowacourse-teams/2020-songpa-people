package com.songpapeople.hashtagmap.exception;

public class AdminException extends HashtagMapException {
    public AdminException(AdminErrorCode adminErrorCode, String detailMessage) {
        super(adminErrorCode.getMessage(), adminErrorCode.getCode(), detailMessage);
    }
}
