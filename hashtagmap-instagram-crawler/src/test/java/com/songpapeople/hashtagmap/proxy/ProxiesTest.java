package com.songpapeople.hashtagmap.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProxiesTest {
    @DisplayName("인자로 넘긴 인덱스에 해당하는 프록시로 잘 설정되는지 테스트")
    @Test
    void setHostAndPort() {
        Queue<Proxy> proxies = new LinkedList<>();
        proxies.offer(new Proxy("123.123.123.123", "80"));

        new Proxies(proxies).setHostAndPort();
        String actualHost = System.getProperty("https.proxyHost");
        String actualPort = System.getProperty("https.proxyPort");

        assertAll(
                () -> assertThat(actualHost).isEqualTo("123.123.123.123"),
                () -> assertThat(actualPort).isEqualTo("80")
        );
    }
}
