package com.songpapeople.hashtagmap.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProxiesTest {
    @DisplayName("인자로 넘긴 인덱스에 해당하는 프록시로 잘 설정되는지 테스트")
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
