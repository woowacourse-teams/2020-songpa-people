package com.songpapeople.hashtagmap.proxy;

import lombok.Getter;

import java.util.Queue;

@Getter
public class Proxies {
    private final Queue<Proxy> proxies;

    public Proxies(Queue<Proxy> proxies) {
        this.proxies = proxies;
    }

    public void setHostAndPort() {
        if (!this.proxies.isEmpty()) {
            Proxy poll = proxies.poll();
            poll.setHostAndPort();
        }
    }

    public int size() {
        return proxies.size();
    }

}
