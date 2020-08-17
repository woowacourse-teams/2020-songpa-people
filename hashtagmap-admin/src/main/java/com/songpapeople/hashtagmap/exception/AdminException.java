package com.songpapeople.hashtagmap.exception;

public class AdminException extends HashtagMapException {
    public AdminException(AdminExceptionStatus adminExceptionStatus) {
        super(adminExceptionStatus.getMessage(), adminExceptionStatus.getCode());
    }

    public AdminException(AdminExceptionStatus adminExceptionStatus, String detailMessage) {
        super(adminExceptionStatus.getMessage(), adminExceptionStatus.getCode(), detailMessage);
    }

    public AdminException(final CommonExceptionStatus commonExceptionStatus, final String detailMessage) {
        super(commonExceptionStatus, detailMessage);
    }
}
