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
        Document doc = Crawler.crawling(FREE_PROXY_LIST_URL);
        String[] splitByNewLine = doc.text().split(System.lineSeparator());
        List<HostAndPort> hostAndPorts = Arrays.stream(splitByNewLine)
            .filter(text -> text.contains(HOST_AND_PORT_DELIMITER))
            .map(x -> x.split(HOST_AND_PORT_DELIMITER))
            .map(y -> new HostAndPort(y[HOST_INDEX], y[PORT_INDEX]))
            .collect(Collectors.toList());
        return new Proxies(hostAndPorts);
    }
}
