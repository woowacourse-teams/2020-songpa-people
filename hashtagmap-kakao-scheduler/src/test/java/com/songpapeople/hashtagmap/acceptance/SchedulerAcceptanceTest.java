package com.songpapeople.hashtagmap.acceptance;

import com.songpapeople.hashtagmap.scheduler.domain.KakaoPlaceScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class SchedulerAcceptanceTest {
    private KakaoPlaceScheduler kakaoPlaceScheduler;

    @BeforeEach
    private void setUp() {
        kakaoPlaceScheduler = new KakaoPlaceScheduler(new PeriodicTrigger(1, TimeUnit.MILLISECONDS));
    }

    @DisplayName("스케쥴링 인수테스트")
    @TestFactory
    public Stream<DynamicTest> schedulingTest() {
        return Stream.of(
                dynamicTest("스케쥴러 실행", () -> {
                    startScheduler();
                }),
                dynamicTest("스케쥴러가 데이터 수집", () -> {

                }),
                dynamicTest("스케쥴러 종료", () -> {
                    endScheduler();
                })
        );
    }

    private void startScheduler() {
        kakaoPlaceScheduler.start();
    }

    private void endScheduler() {
        kakaoPlaceScheduler.end();
    }
}
