package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.proxy.Proxies;

public class ProxySetter {
    private final Proxies proxies;

    public ProxySetter(Proxies proxies) {
        this.proxies = proxies;
    }

    public void setProxy() {
        proxies.clearHostAndPort();
        proxies.setHostAndPort();
    }
}
