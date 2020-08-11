package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KakaoSchedulerTest {

    @DisplayName("Kakao schedule cron 주기 변경하기")
    @Test
    void kakaoScheduleTest() throws InterruptedException {
        //given
        String preExpression = "* * * * * ?";
        String postExpression = "0/1 * * * * ?";

        Map<String, Integer> hitMap = new HashMap<>();
        hitMap.put(preExpression, 0);
        hitMap.put(postExpression, 0);

        CronPeriod cronPeriod = new CronPeriod(preExpression);

        CountDownLatch preCountDownLatch = new CountDownLatch(1);
        CountDownLatch postCountDownLatch = new CountDownLatch(1);

        KakaoScheduler kakaoScheduler = new KakaoScheduler(
                () -> {
                    CronTrigger nowTrigger = (CronTrigger) cronPeriod.getTrigger();
                    String nowTriggerExpression = nowTrigger.getExpression();

                    hitMap.put(nowTriggerExpression, hitMap.get(nowTriggerExpression) + 1);

                    //cron 값을 바꾸고 난 후 postCountDownLatch 카운트
                    if (isExcute(preCountDownLatch)) {
                        postCountDownLatch.countDown();
                    } else {
                        preCountDownLatch.countDown();
                    }
                },
                cronPeriod
        );

        //when
        kakaoScheduler.start();

        preCountDownLatch.await();
        kakaoScheduler.changePeriod(postExpression);

        postCountDownLatch.await();
        kakaoScheduler.end();

        //then
        CronTrigger cronTrigger = (CronTrigger) cronPeriod.getTrigger();

        assertThat(cronTrigger.getExpression()).isEqualTo(postExpression);
        assertThat(hitMap.get(preExpression)).isGreaterThan(0);
        assertThat(hitMap.get(postExpression)).isGreaterThan(0);
    }

    private boolean isExcute(CountDownLatch countDownLatch) {
        return countDownLatch.getCount() == 0;
    }

    @DisplayName("스케쥴러가 이미 실행중이면 Exception 발생")
    @Test
    void isRunningTest() {
        KakaoScheduler kakaoScheduler = new KakaoScheduler(() -> {
        }, new CronPeriod("* * * * * ?"));

        kakaoScheduler.start();

        assertThatThrownBy(kakaoScheduler::start)
                .isInstanceOf(KakaoSchedulerException.class)
                .hasMessage("스케쥴러가 이미 실행중입니다.");

        kakaoScheduler.stop();
    }
}
