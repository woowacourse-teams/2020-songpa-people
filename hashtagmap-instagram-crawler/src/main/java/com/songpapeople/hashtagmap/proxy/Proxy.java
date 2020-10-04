package com.songpapeople.hashtagmap.proxy;

public class Proxy {
    private final String host;
    private final String port;

    public Proxy(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public void setHostAndPort() {
        System.setProperty("https.proxyHost", this.host);
        System.setProperty("https.proxyPort", this.port);
    }

    public void clearProperty() {
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
    }

    @Override
    public String toString() {
        return "Proxy{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
