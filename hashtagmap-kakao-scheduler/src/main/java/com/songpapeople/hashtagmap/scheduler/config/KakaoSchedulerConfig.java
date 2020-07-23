package com.songpapeople.hashtagmap.scheduler.config;

import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import com.songpapeople.hashtagmap.scheduler.service.KakaoSchedulerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;

@EnableScheduling
@Configuration
public class KakaoSchedulerConfig {
    private final KakaoSchedulerService kakaoSchedulerService;

    public KakaoSchedulerConfig(KakaoSchedulerService kakaoSchedulerService) {
        this.kakaoSchedulerService = kakaoSchedulerService;
    }

    @Bean
    public KakaoScheduler kakaoPlaceScheduler() {
        PeriodicTrigger trigger = new PeriodicTrigger(30, TimeUnit.DAYS);
        return new KakaoScheduler(kakaoSchedulerService, trigger);
    }
}
