package com.songpapeople.hashtagmap.proxy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;

import com.songpapeople.hashtagmap.crawler.Crawler;

public class ProxiesFactory {
    private static final String FREE_PROXY_LIST_URL = "https://free-proxy-list.net/";
    private static final String HOST_AND_PORT_DELIMITER = ":";
    private static final int HOST_INDEX = 0;
    private static final int PORT_INDEX = 1;

    public static Proxies create() {
        Document document = Crawler.crawling(FREE_PROXY_LIST_URL);
        String[] hostAndPorts = document.text().split(System.lineSeparator());
        List<Proxy> proxies = createProxies(hostAndPorts);
        return new Proxies(proxies);
    }

    private static List<Proxy> createProxies(String[] hostAndPorts) {
        return Arrays.stream(hostAndPorts)
            .filter(text -> text.contains(HOST_AND_PORT_DELIMITER))
            .map(line -> line.split(HOST_AND_PORT_DELIMITER))
            .map(address -> new Proxy(address[HOST_INDEX], address[PORT_INDEX]))
            .collect(Collectors.toList());
    }
}
