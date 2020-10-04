package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.proxy.Proxies;
import com.songpapeople.hashtagmap.proxy.Proxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class ProxySetterTest {
    @DisplayName("프록시 set테스트")
    @Test
    void init() {
        Queue<Proxy> proxies = new LinkedList<>();
        proxies.offer(new Proxy("1", "1"));
        proxies.offer(new Proxy("2", "2"));
        proxies.offer(new Proxy("3", "4"));
        ProxySetter proxySetter = new ProxySetter(new Proxies(proxies));

        proxySetter.setProxy();

        assertThat(System.getProperty("https.proxyHost")).isBetween("1", "3");
    }
}
