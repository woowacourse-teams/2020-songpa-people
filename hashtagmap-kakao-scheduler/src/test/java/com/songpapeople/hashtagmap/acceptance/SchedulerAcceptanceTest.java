package com.songpapeople.hashtagmap.acceptance;

import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoSchedulerTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchedulerAcceptanceTest {
    private KakaoScheduler kakaoScheduler;

    @Mock
    private KakaoSchedulerTask kakaoSchedulerTask;

    @BeforeEach
    private void setUp() {
        /**
         * 주기: period * TimeUnit
         */
        kakaoScheduler = new KakaoScheduler(kakaoSchedulerTask,
                new PeriodicTrigger(1, TimeUnit.SECONDS));
    }

    @DisplayName("스케쥴링 인수테스트")
    @TestFactory
    public Stream<DynamicTest> schedulingTest() {
        doNothing().when(kakaoSchedulerTask).collectData();
        return Stream.of(
                dynamicTest("스케쥴러 실행", () -> {
                    kakaoScheduler.start();

                    verify(kakaoSchedulerTask).collectData();
                }),
                /**
                 * 스케쥴러를 실행할 때 task가 1회 호출되기 때문에 총 3회 실행된다.
                 */
                dynamicTest("스케쥴러가 데이터 수집", () -> {
                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(1000L);
                    }

                    verify(kakaoSchedulerTask, times(3)).collectData();

                    kakaoScheduler.end();
                })
        );
    }
}
