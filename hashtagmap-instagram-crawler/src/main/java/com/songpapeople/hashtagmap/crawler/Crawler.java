package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Crawler {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36";
    private static final int HOLDING_TIME = 10000000;
    public static int count = 0;
    public static int count404 = 0;
    public static int count429 = 0;

    public String crawl(String url) {
        count++;
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(HOLDING_TIME)
                    .get()
                    .body()
                    .toString();
        } catch (HttpStatusException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getMessage() + " " + url);
            if (e.getStatusCode() == 404) {
                count404++;
            } else if (e.getStatusCode() == 429) {
                count429++;
                throw new CrawlerException(CrawlerExceptionStatus.TOO_MANY_REQUEST);
            }
            throw new CrawlerException(CrawlerExceptionStatus.URL_NOT_CONNECT);

        } catch (IOException e) {
            e.printStackTrace();
            throw new CrawlerException(CrawlerExceptionStatus.URL_NOT_CONNECT);
        }
    }
}
