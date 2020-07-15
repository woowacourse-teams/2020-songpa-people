package com.songpapeople.hashtagmap.proxy;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ProxiesTest {

    @Test
    void setHostAndPort() {
        Proxies proxies = new Proxies(Arrays.asList(
            new Proxy("123.123.123.1", "11"),
            new Proxy("123.123.123.2", "12"),
            new Proxy("123.123.123.3", "13")
        ));

        proxies.setHostAndPort(1);
        String actualHost = System.getProperty("http.proxyHost");
        String actualPort = System.getProperty("http.proxyPort");

        assertAll(
            () -> assertThat(actualHost).isEqualTo("123.123.123.2"),
            () -> assertThat(actualPort).isEqualTo("12")
        );
    }
}