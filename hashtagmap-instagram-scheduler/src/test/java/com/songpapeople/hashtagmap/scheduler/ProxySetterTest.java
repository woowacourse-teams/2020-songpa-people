package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.proxy.Proxies;
import com.songpapeople.hashtagmap.proxy.Proxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ProxySetterTest {
    @DisplayName("프록시 set테스트")
    @Test
    void init() {
        Proxies proxies = new Proxies(Arrays.asList(
                new Proxy("1", "1"),
                new Proxy("2", "2"),
                new Proxy("3", "3"))
        );
        ProxySetter proxySetter = new ProxySetter(proxies);

        proxySetter.set();

        assertThat(System.getProperty("http.proxyHost")).isBetween("1", "3");
    }
}
