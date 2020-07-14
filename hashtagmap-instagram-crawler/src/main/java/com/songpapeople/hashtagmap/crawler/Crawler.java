package com.songpapeople.hashtagmap.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";

    public static Document crawling(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException e) {
            throw new IllegalArgumentException("연결할 수 없는 url 입니다.");
        }
    }
}
