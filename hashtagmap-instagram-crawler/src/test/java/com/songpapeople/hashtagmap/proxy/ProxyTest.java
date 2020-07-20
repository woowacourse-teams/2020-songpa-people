package com.songpapeople.hashtagmap.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProxyTest {
    @DisplayName("프록시 설정 테스트")
    @Test
    void setHostAndPort() {
        String inputHost = "123.23.12.15";
        String inputPort = "8080";
        Proxy proxy = new Proxy(inputHost, inputPort);
        proxy.setHostAndPort();

        String actualHost = System.getProperty("http.proxyHost");
        String actualPort = System.getProperty("http.proxyPort");

        assertAll(
                () -> assertThat(actualHost).isEqualTo(inputHost),
                () -> assertThat(actualPort).isEqualTo(inputPort)
        );
    }
}
