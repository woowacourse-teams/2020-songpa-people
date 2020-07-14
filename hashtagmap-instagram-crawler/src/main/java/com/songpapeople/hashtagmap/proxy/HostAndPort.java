package com.songpapeople.hashtagmap.proxy;

public class HostAndPort {
    private final String host;
    private final String port;

    public HostAndPort(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public void setProxy() {
        System.setProperty("http.proxyHost", this.host);
        System.setProperty("http.proxyPort", this.port);
    }
}
