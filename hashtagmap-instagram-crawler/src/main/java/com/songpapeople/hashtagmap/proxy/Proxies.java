package com.songpapeople.hashtagmap.proxy;

import java.util.List;

public class Proxies {
    private final List<HostAndPort> hostAndPorts;

    public Proxies(List<HostAndPort> hostAndPorts) {
        this.hostAndPorts = hostAndPorts;
    }

    public void setProxy(int random) {
        hostAndPorts.get(random).setProxy();
    }
}
