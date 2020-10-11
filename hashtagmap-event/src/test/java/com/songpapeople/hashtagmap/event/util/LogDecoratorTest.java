package com.songpapeople.hashtagmap.event.util;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class LogDecoratorTest {

    @Test
    void decorate() throws InterruptedException {
        //given
        MDC.put("test", "test");
        LogDecorator logDecorator = new LogDecorator(MDC.getCopyOfContextMap());

        CountDownLatch countDownLatch = new CountDownLatch(1);
        logDecorator.decorate(() -> {
            assertThat(MDC.get("test")).isEqualTo("test");
            countDownLatch.countDown();
        }).run();

        countDownLatch.await();
    }
}