package com.songpapeople.hashtagmap.exception;

public class CoreException extends HashtagMapException {
    public CoreException(CoreExceptionStatus coreExceptionStatus) {
        super(coreExceptionStatus.getMessage(), coreExceptionStatus.getCode());
    }

    public CoreException(CoreExceptionStatus coreExceptionStatus, String detailMessage) {
        super(coreExceptionStatus.getMessage(), coreExceptionStatus.getCode(), detailMessage);
    }
}
