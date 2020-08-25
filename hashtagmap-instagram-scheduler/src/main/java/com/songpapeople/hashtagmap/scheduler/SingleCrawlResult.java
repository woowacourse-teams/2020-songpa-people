package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import lombok.Getter;

@Getter
public class SingleCrawlResult {
    private final Long hashtagCount;

    private SingleCrawlResult(Long hashtagCount) {
        this.hashtagCount = hashtagCount;
    }

    public static SingleCrawlResult of(CrawlingDto crawler) {
        return new SingleCrawlResult(crawler.getHashtagCount());
    }
}
