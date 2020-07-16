package com.songpapeople.hashtagmap.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.songpapeople.hashtagmap.crawler.Crawler;

public class ProxiesFactory {
    private static final String FREE_PROXY_LIST_URL = "https://free-proxy-list.net/";
    private static final String PROXY_REGEX = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)";
    private static final int HOST_INDEX = 1;
    private static final int PORT_INDEX = 2;

    public static Proxies create() {
        Document document = Crawler.crawling(FREE_PROXY_LIST_URL);
        Pattern pattern = Pattern.compile(PROXY_REGEX);
        Matcher matcher = pattern.matcher(document.body().toString());
        List<Proxy> proxies = new ArrayList<>();
        while (matcher.find()) {
            proxies.add(new Proxy(matcher.group(HOST_INDEX), matcher.group(PORT_INDEX)));
        }
        return new Proxies(proxies);
    }
}