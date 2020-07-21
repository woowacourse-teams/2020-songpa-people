package com.songpapeople.hashtagmap.proxy;

import java.util.List;

public class Proxies {
    private final List<Proxy> proxies;

    public Proxies(List<Proxy> proxies) {
        this.proxies = proxies;
    }

    public void setHostAndPort(int random) {
        proxies.get(random).setHostAndPort();
    }

    public int size() {
        return proxies.size();
    }
}
