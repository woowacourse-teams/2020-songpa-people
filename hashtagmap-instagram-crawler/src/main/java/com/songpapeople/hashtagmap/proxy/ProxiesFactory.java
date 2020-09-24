package com.songpapeople.hashtagmap.proxy;

import com.songpapeople.hashtagmap.crawler.Crawler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ProxiesFactory {
    private static final String FREE_PROXY_LIST_URL = "https://free-proxy-list.net/";
    private static final String TABLE_START = "<tbody>";
    private static final String TABLE_END = "</tbody>";
    private static final String PROXY_DELIMITER = "</tr>";
    private static final String HTTPS_PROXY = "<td class=\"hx\">yes</td>";
    private static final String PROXY_START = "<td>";
    private static final String PROXY_END = "</td>";
    private static final int HOST_INDEX = 0;
    private static final int PORT_INDEX = 1;

    public static Proxies create(Crawler crawler) {
        String body = crawler.crawl(FREE_PROXY_LIST_URL);
        List<String[]> unProcessedProxies = splitProxiesTable(body);
        Queue<Proxy> proxies = extractProxies(unProcessedProxies);
        return new Proxies(proxies);
    }

    private static List<String[]> splitProxiesTable(String body) {
        String proxyTables = subStringBetween(TABLE_START, TABLE_END, body);
        return Arrays.stream(proxyTables.split(PROXY_DELIMITER))
                .filter(proxyTable -> proxyTable.contains(HTTPS_PROXY))
                .map(httpsProxyTable -> httpsProxyTable.split(PROXY_END))
                .collect(Collectors.toList());
    }

    private static String subStringBetween(String from, String to, String content) {
        int fromIndex = content.indexOf(from);
        int toIndex = content.indexOf(to);
        return content.substring(fromIndex + from.length(), toIndex);
    }

    private static Queue<Proxy> extractProxies(List<String[]> unProcessedProxies) {
        Queue<Proxy> proxies = new LinkedList<>();
        for (String[] unProcessedProxy : unProcessedProxies) {
            String host = subStringToLast(PROXY_START, unProcessedProxy[HOST_INDEX]);
            String port = subStringToLast(PROXY_START, unProcessedProxy[PORT_INDEX]);
            proxies.add(new Proxy(host, port));
        }
        return proxies;
    }

    private static String subStringToLast(String from, String content) {
        int fromIndex = content.indexOf(from);
        return content.substring(fromIndex + from.length());
    }
}
