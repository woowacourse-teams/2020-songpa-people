package com.songpapeople.hashtagmap.scheduler.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoSchedulerTest {

    @DisplayName("Kakao schedule cron 주기 변경하기")
    @Test
    void name() throws InterruptedException {
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
                    if (preCountDownLatch.getCount() == 0) {
                        postCountDownLatch.countDown();
                    }
                    preCountDownLatch.countDown();

                },
                cronPeriod
        );
        kakaoScheduler.start();

        //when
        preCountDownLatch.await();
        kakaoScheduler.changePeriod(postExpression);

        postCountDownLatch.await();
        kakaoScheduler.end();

        //then
        CronTrigger cronTrigger = (CronTrigger) cronPeriod.getTrigger();

        assertThat(cronTrigger.getExpression()).isEqualTo(postExpression);
        assertThat(hitMap.get(preExpression)).isEqualTo(1);
        assertThat(hitMap.get(postExpression)).isEqualTo(1);
    }

}
