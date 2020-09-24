package com.songpapeople.hashtagmap.proxy;

import com.songpapeople.hashtagmap.crawler.Crawler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProxiesFactoryTest {

    @DisplayName("프록시 사이트에 프록시 주소를 크롤링하여 정상적으로 Proxies를 만드는지 테스트")
    @Test
    void create() {
        assertThat(ProxiesFactory.create(new Crawler()).size()).isGreaterThan(200);
    }

    @DisplayName("asd")
    @Test
    void name() {
        Proxies proxies = ProxiesFactory.create(new Crawler());
        int count = 0;
        System.out.println(proxies.size());
        for (int i = 0; i < proxies.size(); i++) {
            proxies.setHostAndPort();
            HttpURLConnection httpURLConnection;
            try {
                httpURLConnection = (HttpURLConnection) new URL("https://www.instagram.com/").openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.usingProxy()) {
                    count++;
                }
                httpURLConnection.disconnect();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
            }

        }
        System.out.println(count);
    }
}
