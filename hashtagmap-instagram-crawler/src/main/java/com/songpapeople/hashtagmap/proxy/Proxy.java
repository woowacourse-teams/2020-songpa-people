package com.songpapeople.hashtagmap.proxy;

import lombok.ToString;

@ToString
public class Proxy {
    private final String host;
    private final String port;

    public Proxy(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public void setHostAndPort() {
        System.setProperty("http.proxyHost", this.host);
        System.setProperty("http.proxyPort", this.port);
    }
}
