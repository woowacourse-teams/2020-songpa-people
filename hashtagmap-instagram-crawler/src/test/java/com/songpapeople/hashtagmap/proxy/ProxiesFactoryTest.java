package com.songpapeople.hashtagmap.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ProxiesFactoryTest {

    @DisplayName("프록시 사이트에 프록시 주소를 크롤링하여 정상적으로 Proxies를 만드는지 테스트")
    @Test
    void create() {
        assertThat(ProxiesFactory.create().size()).isGreaterThan(200);
    }
}
