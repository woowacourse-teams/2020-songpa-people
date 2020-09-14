package com.songpapeople.hashtagmap.scheduler.config;

import com.songpapeople.hashtagmap.scheduler.domain.CronPeriod;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoSchedulerTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class KakaoSchedulerConfig {
    private static final String EXPRESSION = "0 0 2 1 * ?";

    private final KakaoSchedulerTask kakaoSchedulerTask;

    public KakaoSchedulerConfig(KakaoSchedulerTask kakaoSchedulerTask) {
        this.kakaoSchedulerTask = kakaoSchedulerTask;
    }

    @Bean
    public KakaoScheduler kakaoPlaceScheduler() {
        return new KakaoScheduler(kakaoSchedulerTask::sourceEvent, new CronPeriod(EXPRESSION));
    }
}
