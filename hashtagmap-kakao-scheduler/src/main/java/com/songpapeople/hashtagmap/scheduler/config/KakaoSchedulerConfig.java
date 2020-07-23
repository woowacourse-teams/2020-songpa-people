package com.songpapeople.hashtagmap.scheduler.config;

import com.songpapeople.hashtagmap.scheduler.domain.KakaoPlaceScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;

@EnableScheduling
@Configuration
public class KakaoSchedulerConfig {
    @Bean
    public KakaoPlaceScheduler kakaoPlaceScheduler() {
        return new KakaoPlaceScheduler(new PeriodicTrigger(30, TimeUnit.DAYS));
    }
}
