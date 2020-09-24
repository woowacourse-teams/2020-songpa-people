package com.songpapeople.hashtagmap.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProxyTest {
    @DisplayName("프록시 설정 테스트")
    @Test
    void setHostAndPort() {
        String inputHost = "123.23.12.15";
        String inputPort = "8080";
        Proxy proxy = new Proxy(inputHost, inputPort);
        proxy.setHostAndPort();

        String actualHost = System.getProperty("https.proxyHost");
        String actualPort = System.getProperty("https.proxyPort");

        assertAll(
                () -> assertThat(actualHost).isEqualTo(inputHost),
                () -> assertThat(actualPort).isEqualTo(inputPort)
        );
    }

    @DisplayName("")
    @Test
    void name() throws IOException {
        Proxy proxy = new Proxy("https://themiso.kr/", "443");
        proxy.setHostAndPort();
        String instagramUrl = "https://www.instagram.com/";
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(instagramUrl).openConnection();
        System.out.println(urlConnection.usingProxy());
    }
}
