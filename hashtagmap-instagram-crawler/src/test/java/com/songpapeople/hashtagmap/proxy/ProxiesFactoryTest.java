package com.songpapeople.hashtagmap.proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProxiesFactoryTest {

    @DisplayName("프록시 사이트에 프록시 주소를 크롤링하여 정상적으로 Proxies를 만드는지 테스트")
    @Test
    void create() {
        assertThat(ProxiesFactory.create().size()).isGreaterThan(200);
    }

    @DisplayName("유틸성 클래스라 private 생성자를 가지고 있다.")
    @Test
    void constructorTest() throws NoSuchMethodException {
        Constructor<ProxiesFactory> declaredConstructor = ProxiesFactory.class.getDeclaredConstructor((Class<?>[]) null);
        Assertions.assertThat(declaredConstructor.isAccessible()).isFalse();
    }
}
