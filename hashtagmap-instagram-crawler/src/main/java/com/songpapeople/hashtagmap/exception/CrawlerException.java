package com.songpapeople.hashtagmap.exception;

public class CrawlerException extends HashtagMapException {

    public CrawlerException(CrawlerExceptionStatus crawlerExceptionStatus) {
        super(crawlerExceptionStatus.getMessage(), crawlerExceptionStatus.getStatusCode());
    }
}
