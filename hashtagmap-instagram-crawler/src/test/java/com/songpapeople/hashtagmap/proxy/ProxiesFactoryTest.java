package com.songpapeople.hashtagmap.proxy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

class ProxiesFactoryTest {

    @Test
    void create() {
        assertThat(ProxiesFactory.create().size()).isGreaterThan(200);
    }
}