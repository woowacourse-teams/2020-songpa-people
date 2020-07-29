package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Crawler {
    private static final String USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";
    private static final int HOLDING_TIME = 7000;

    public static String crawl(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(HOLDING_TIME)
                    .get()
                    .body()
                    .toString();
        } catch (IOException e) {
            throw new CrawlerException(CrawlerExceptionStatus.URL_NOT_CONNECT);
        }
    }
}
