package com.songpapeople.hashtagmap.scheduler.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KakaoSchedulerTest {
    private KakaoScheduler kakaoScheduler;

    @Mock
    private KakaoSchedulerTask kakaoSchedulerTask;

    @DisplayName("스케쥴러 비동기 동작 확인")
    @TestFactory
    public Stream<DynamicTest> schedulingTest() {
        doNothing().when(kakaoSchedulerTask).collectData();

        CountDownLatch runnableLatch = new CountDownLatch(1);
        CountDownLatch waitLatch = new CountDownLatch(1);
        kakaoScheduler = new KakaoScheduler(
                () -> {
                    kakaoSchedulerTask.collectData();
                    runnableLatch.countDown();
                    try {
                        waitLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                new PeriodicTrigger(30, TimeUnit.SECONDS));

        return Stream.of(
                dynamicTest("스케쥴러 실행", () -> {
                    kakaoScheduler.start();
                    verify(kakaoSchedulerTask).collectData();
                }),
                dynamicTest("스케쥴러가 데이터 수집", () -> {
                    runnableLatch.await();
                    verify(kakaoSchedulerTask, times(1)).collectData();
                    waitLatch.countDown();
                    kakaoScheduler.end();
                })
        );
    }
}
