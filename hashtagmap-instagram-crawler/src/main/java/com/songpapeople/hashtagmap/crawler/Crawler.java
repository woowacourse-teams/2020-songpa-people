package com.songpapeople.hashtagmap.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.songpapeople.hashtagmap.exception.CrawlingUrlException;

public class Crawler {
    private static final String USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";
    private static final int HOLDING_TIME = 5000;

    public static Document crawling(String url) {
        try {
            return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(HOLDING_TIME)
                .get();
        } catch (IOException e) {
            throw new CrawlingUrlException();
        }
    }
}
